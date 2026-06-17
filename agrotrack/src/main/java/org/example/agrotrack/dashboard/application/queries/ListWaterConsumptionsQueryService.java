package org.example.agrotrack.dashboard.application.queries;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.domain.model.WaterConsumption;
import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.repositories.JpaWaterConsumptionRepository;
import org.example.agrotrack.dashboard.interfaces.transform.WaterConsumptionEntityAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListWaterConsumptionsQueryService {

    private final JpaWaterConsumptionRepository repository;
    private final WaterConsumptionEntityAssembler entityAssembler;
    private final PlotScopedMetricsFilter plotScopedMetricsFilter;

    @Transactional(readOnly = true)
    public Result<List<WaterConsumption>, ApplicationError> findAll(String userId) {
        List<WaterConsumption> consumptions = repository.findAll().stream()
                .map(entityAssembler::toDomain)
                .toList();
        List<WaterConsumption> scopedConsumptions = plotScopedMetricsFilter.applyUserScope(
                userId,
                consumptions,
                WaterConsumption::getPlotId
        );
        return Result.success(scopedConsumptions);
    }
}
