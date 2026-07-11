package org.example.agrotrack.support.infrastructure.adapters;

import org.example.agrotrack.support.domain.model.aggregates.SupportTicket;
import org.example.agrotrack.support.domain.repositories.SupportTicketRepository;
import org.example.agrotrack.support.infrastructure.assemblers.SupportTicketPersistenceAssembler;
import org.example.agrotrack.support.infrastructure.repositories.SupportTicketPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SupportTicketRepositoryImpl implements SupportTicketRepository {

    private final SupportTicketPersistenceRepository persistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public SupportTicketRepositoryImpl(
            SupportTicketPersistenceRepository persistenceRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.persistenceRepository = persistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<SupportTicket> findById(String id) {
        return persistenceRepository.findById(id).map(SupportTicketPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<SupportTicket> findAll() {
        return persistenceRepository.findAll().stream()
                .map(SupportTicketPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<SupportTicket> findByUserIdOrderByCreatedAtDesc(String userId) {
        return persistenceRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(SupportTicketPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public SupportTicket save(SupportTicket ticket) {
        if (ticket.getId() == null) {
            var saved = persistenceRepository.save(SupportTicketPersistenceAssembler.toPersistenceFromDomain(ticket));
            var savedTicket = SupportTicketPersistenceAssembler.toDomainFromPersistence(saved);
            savedTicket.onCreated();
            publishAndClearDomainEvents(savedTicket);
            return savedTicket;
        }
        var entity = persistenceRepository.findById(ticket.getId())
                .orElseThrow(() -> new IllegalStateException("Support ticket not found: " + ticket.getId()));
        SupportTicketPersistenceAssembler.updatePersistenceFromDomain(entity, ticket);
        var saved = persistenceRepository.save(entity);
        var savedTicket = SupportTicketPersistenceAssembler.toDomainFromPersistence(saved);
        publishAndClearDomainEvents(ticket);
        return savedTicket;
    }

    @Override
    public long count() {
        return persistenceRepository.count();
    }

    private void publishAndClearDomainEvents(SupportTicket ticket) {
        ticket.domainEvents().forEach(eventPublisher::publishEvent);
        ticket.clearDomainEvents();
    }
}
