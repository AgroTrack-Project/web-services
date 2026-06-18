package org.example.agrotrack.support.application.internal.commandservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.support.application.commandservices.SupportTicketCommandService;
import org.example.agrotrack.support.application.ports.UserReferencePort;
import org.example.agrotrack.support.domain.model.TicketStatus;
import org.example.agrotrack.support.domain.model.aggregates.SupportTicket;
import org.example.agrotrack.support.domain.model.commands.CloseSupportTicketCommand;
import org.example.agrotrack.support.domain.model.commands.CreateSupportTicketCommand;
import org.example.agrotrack.support.domain.model.valueobjects.TicketMessage;
import org.example.agrotrack.support.domain.model.valueobjects.TicketSubject;
import org.example.agrotrack.support.domain.model.valueobjects.UserId;
import org.example.agrotrack.support.domain.repositories.SupportTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class SupportTicketCommandServiceImpl implements SupportTicketCommandService {

    private final SupportTicketRepository repository;
    private final UserReferencePort userReferencePort;

    public SupportTicketCommandServiceImpl(
            SupportTicketRepository repository,
            UserReferencePort userReferencePort
    ) {
        this.repository = repository;
        this.userReferencePort = userReferencePort;
    }

    @Override
    @Transactional
    public Result<SupportTicket, ApplicationError> handle(CreateSupportTicketCommand command) {
        try {
            UserId userIdValue = new UserId(command.userId());
            TicketSubject subjectValue = new TicketSubject(command.subject());
            TicketMessage messageValue = new TicketMessage(command.message());

            if (!userReferencePort.userExists(userIdValue.value())) {
                return Result.failure(ApplicationError.validationError("user_id", "User does not exist"));
            }

            SupportTicket ticket = SupportTicket.open(userIdValue, subjectValue, messageValue, Instant.now());
            return Result.success(repository.save(ticket));
        } catch (IllegalArgumentException ex) {
            return Result.failure(ApplicationError.validationError("SupportTicket", ex.getMessage()));
        }
    }

    @Override
    @Transactional
    public Result<SupportTicket, ApplicationError> handle(CloseSupportTicketCommand command) {
        String requestedStatus = command.requestedStatus();
        if (requestedStatus != null && !requestedStatus.isBlank()
                && !TicketStatus.CLOSED.name().equalsIgnoreCase(requestedStatus.trim())) {
            return Result.failure(ApplicationError.validationError(
                    "status",
                    "PUT only supports closing tickets (status=CLOSED)"
            ));
        }

        String ticketId = command.ticketId();
        if (ticketId == null || ticketId.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "ticket id is required"));
        }

        var ticketOptional = repository.findById(ticketId);
        if (ticketOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("SupportTicket", ticketId));
        }

        SupportTicket ticket = ticketOptional.get();
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
        return Result.success(repository.save(ticket));
    }
}
