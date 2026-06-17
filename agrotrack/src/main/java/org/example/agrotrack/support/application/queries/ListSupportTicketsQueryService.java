package org.example.agrotrack.support.application.queries;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.support.domain.model.SupportTicket;
import org.example.agrotrack.support.infrastructure.persistence.jpa.repositories.JpaSupportTicketRepository;
import org.example.agrotrack.support.interfaces.transform.SupportTicketEntityAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListSupportTicketsQueryService {

    private final JpaSupportTicketRepository repository;
    private final SupportTicketEntityAssembler entityAssembler;

    @Transactional(readOnly = true)
    public Result<List<SupportTicket>, ApplicationError> findAll(String userId) {
        if (userId != null && !userId.isBlank()) {
            return Result.success(
                    repository.findByUserIdOrderByCreatedAtDesc(userId.trim()).stream()
                            .map(entityAssembler::toDomain)
                            .toList()
            );
        }
        return Result.success(
                repository.findAll().stream()
                        .map(entityAssembler::toDomain)
                        .sorted((left, right) -> right.getCreatedAt().compareTo(left.getCreatedAt()))
                        .toList()
        );
    }
}
