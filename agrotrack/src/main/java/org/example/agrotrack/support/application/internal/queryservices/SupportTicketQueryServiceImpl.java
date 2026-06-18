package org.example.agrotrack.support.application.internal.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.support.application.queryservices.SupportTicketQueryService;
import org.example.agrotrack.support.domain.model.aggregates.SupportTicket;
import org.example.agrotrack.support.domain.model.queries.GetSupportTicketByIdQuery;
import org.example.agrotrack.support.domain.model.queries.ListSupportTicketsQuery;
import org.example.agrotrack.support.domain.repositories.SupportTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupportTicketQueryServiceImpl implements SupportTicketQueryService {

    private final SupportTicketRepository repository;

    public SupportTicketQueryServiceImpl(SupportTicketRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<List<SupportTicket>, ApplicationError> handle(ListSupportTicketsQuery query) {
        String userId = query.userId();
        if (userId != null && !userId.isBlank()) {
            return Result.success(repository.findByUserIdOrderByCreatedAtDesc(userId.trim()));
        }
        return Result.success(
                repository.findAll().stream()
                        .sorted((left, right) -> right.getCreatedAt().compareTo(left.getCreatedAt()))
                        .toList()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Result<SupportTicket, ApplicationError> handle(GetSupportTicketByIdQuery query) {
        String ticketId = query.id();
        if (ticketId == null || ticketId.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "ticket id is required"));
        }
        var ticketOptional = repository.findById(ticketId);
        if (ticketOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("SupportTicket", ticketId));
        }
        return Result.success(ticketOptional.get());
    }
}
