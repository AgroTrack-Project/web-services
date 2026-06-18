package org.example.agrotrack.support.infrastructure.assemblers;

import org.example.agrotrack.support.domain.model.aggregates.SupportTicket;
import org.example.agrotrack.support.domain.model.valueobjects.TicketMessage;
import org.example.agrotrack.support.domain.model.valueobjects.TicketSubject;
import org.example.agrotrack.support.domain.model.valueobjects.UserId;
import org.example.agrotrack.support.infrastructure.entities.SupportTicketPersistenceEntity;

public final class SupportTicketPersistenceAssembler {

    private SupportTicketPersistenceAssembler() {
    }

    public static SupportTicket toDomainFromPersistence(SupportTicketPersistenceEntity entity) {
        return SupportTicket.restore(
                entity.getId(),
                new UserId(entity.getUserId()),
                new TicketSubject(entity.getSubject()),
                new TicketMessage(entity.getMessage()),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getRespondedAt()
        );
    }

    public static SupportTicketPersistenceEntity toPersistenceFromDomain(SupportTicket ticket) {
        SupportTicketPersistenceEntity entity = new SupportTicketPersistenceEntity();
        entity.setUserId(ticket.getUserId().value());
        entity.setSubject(ticket.getSubject().value());
        entity.setMessage(ticket.getMessage().value());
        entity.setStatus(ticket.getStatus());
        entity.setCreatedAt(ticket.getCreatedAt());
        entity.setRespondedAt(ticket.getRespondedAt());
        return entity;
    }

    public static void updatePersistenceFromDomain(SupportTicketPersistenceEntity entity, SupportTicket ticket) {
        entity.setStatus(ticket.getStatus());
        entity.setRespondedAt(ticket.getRespondedAt());
    }
}
