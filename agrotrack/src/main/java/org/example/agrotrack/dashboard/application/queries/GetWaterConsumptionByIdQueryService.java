package org.example.agrotrack.dashboard.application.queries;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.domain.model.WaterConsumption;
import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.repositories.JpaWaterConsumptionRepository;
import org.example.agrotrack.dashboard.interfaces.transform.WaterConsumptionEntityAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetWaterConsumptionByIdQueryService {

    private final JpaWaterConsumptionRepository repository;
    private final WaterConsumptionEntityAssembler entityAssembler;

    @Transactional(readOnly = true)
    public Result<WaterConsumption, ApplicationError> findById(String id) {
        if (id == null || id.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "water consumption id is required"));
        }
        var entityOptional = repository.findById(id);
        if (entityOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("WaterConsumption", id));
        }
        return Result.success(entityAssembler.toDomain(entityOptional.get()));
    }
}
