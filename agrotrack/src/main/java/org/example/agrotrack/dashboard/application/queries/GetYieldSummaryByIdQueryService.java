package org.example.agrotrack.dashboard.application.queries;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.domain.model.YieldSummary;
import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.repositories.JpaYieldSummaryRepository;
import org.example.agrotrack.dashboard.interfaces.transform.YieldSummaryEntityAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetYieldSummaryByIdQueryService {

    private final JpaYieldSummaryRepository repository;
    private final YieldSummaryEntityAssembler entityAssembler;

    @Transactional(readOnly = true)
    public Result<YieldSummary, ApplicationError> findById(String id) {
        if (id == null || id.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "yield summary id is required"));
        }
        var entityOptional = repository.findById(id);
        if (entityOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("YieldSummary", id));
        }
        return Result.success(entityAssembler.toDomain(entityOptional.get()));
    }
}
