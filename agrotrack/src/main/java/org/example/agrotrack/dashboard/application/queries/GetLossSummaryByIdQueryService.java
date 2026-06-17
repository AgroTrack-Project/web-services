package org.example.agrotrack.dashboard.application.queries;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.domain.model.LossSummary;
import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.repositories.JpaLossSummaryRepository;
import org.example.agrotrack.dashboard.interfaces.transform.LossSummaryEntityAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetLossSummaryByIdQueryService {

    private final JpaLossSummaryRepository repository;
    private final LossSummaryEntityAssembler entityAssembler;

    @Transactional(readOnly = true)
    public Result<LossSummary, ApplicationError> findById(String id) {
        if (id == null || id.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "loss summary id is required"));
        }
        var entityOptional = repository.findById(id);
        if (entityOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("LossSummary", id));
        }
        return Result.success(entityAssembler.toDomain(entityOptional.get()));
    }
}
