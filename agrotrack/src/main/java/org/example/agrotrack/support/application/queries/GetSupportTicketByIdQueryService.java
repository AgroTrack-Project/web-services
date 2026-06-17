package org.example.agrotrack.support.application.queries;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.support.domain.model.SupportTicket;
import org.example.agrotrack.support.infrastructure.persistence.jpa.repositories.JpaSupportTicketRepository;
import org.example.agrotrack.support.interfaces.transform.SupportTicketEntityAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetSupportTicketByIdQueryService {

    private final JpaSupportTicketRepository repository;
    private final SupportTicketEntityAssembler entityAssembler;

    @Transactional(readOnly = true)
    public Result<SupportTicket, ApplicationError> findById(String ticketId) {
        if (ticketId == null || ticketId.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "ticket id is required"));
        }
        var entityOptional = repository.findById(ticketId);
        if (entityOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("SupportTicket", ticketId));
        }
        return Result.success(entityAssembler.toDomain(entityOptional.get()));
    }
}
