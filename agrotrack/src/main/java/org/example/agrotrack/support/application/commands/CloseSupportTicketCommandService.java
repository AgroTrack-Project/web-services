package org.example.agrotrack.support.application.commands;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.support.domain.model.SupportTicket;
import org.example.agrotrack.support.domain.model.TicketStatus;
import org.example.agrotrack.support.infrastructure.persistence.jpa.repositories.JpaSupportTicketRepository;
import org.example.agrotrack.support.interfaces.transform.SupportTicketEntityAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CloseSupportTicketCommandService {

    private final JpaSupportTicketRepository repository;
    private final SupportTicketEntityAssembler entityAssembler;

    @Transactional
    public Result<SupportTicket, ApplicationError> close(String ticketId) {
        if (ticketId == null || ticketId.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "ticket id is required"));
        }

        var entityOptional = repository.findById(ticketId);
        if (entityOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("SupportTicket", ticketId));
        }

        var entity = entityOptional.get();
        SupportTicket ticket = entityAssembler.toDomain(entity);
        if (ticket.isClosed()) {
            return Result.success(ticket);
        }
        if (!ticket.canBeClosed()) {
            return Result.failure(ApplicationError.businessRuleViolation(
                    "ticket_close",
                    "Ticket cannot be closed from status %s".formatted(ticket.getStatus())
            ));
        }
        try {
            ticket.close(Instant.now());
        } catch (IllegalStateException ex) {
            return Result.failure(ApplicationError.businessRuleViolation("ticket_close", ex.getMessage()));
        }
        entityAssembler.updateEntityFromDomain(entity, ticket);
        return Result.success(entityAssembler.toDomain(repository.save(entity)));
    }

    @Transactional(readOnly = true)
    public Result<SupportTicket, ApplicationError> closeIfRequested(String ticketId, String requestedStatus) {
        if (requestedStatus != null && !requestedStatus.isBlank()
                && !TicketStatus.CLOSED.name().equalsIgnoreCase(requestedStatus.trim())) {
            return Result.failure(ApplicationError.validationError(
                    "status",
                    "PUT only supports closing tickets (status=CLOSED)"
            ));
        }
        return close(ticketId);
    }
}
