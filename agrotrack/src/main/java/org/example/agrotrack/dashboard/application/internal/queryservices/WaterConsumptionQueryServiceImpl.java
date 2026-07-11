package org.example.agrotrack.dashboard.application.internal.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.application.internal.PlotScopedMetricsFilter;
import org.example.agrotrack.dashboard.application.queryservices.WaterConsumptionQueryService;
import org.example.agrotrack.dashboard.domain.model.aggregates.WaterConsumption;
import org.example.agrotrack.dashboard.domain.model.queries.GetWaterConsumptionByIdQuery;
import org.example.agrotrack.dashboard.domain.model.queries.ListWaterConsumptionsQuery;
import org.example.agrotrack.dashboard.domain.repositories.WaterConsumptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WaterConsumptionQueryServiceImpl implements WaterConsumptionQueryService {

    private final WaterConsumptionRepository repository;
    private final PlotScopedMetricsFilter plotScopedMetricsFilter;

    public WaterConsumptionQueryServiceImpl(
            WaterConsumptionRepository repository,
            PlotScopedMetricsFilter plotScopedMetricsFilter
    ) {
        this.repository = repository;
        this.plotScopedMetricsFilter = plotScopedMetricsFilter;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<List<WaterConsumption>, ApplicationError> handle(ListWaterConsumptionsQuery query) {
        List<WaterConsumption> consumptions = repository.findAll();
        List<WaterConsumption> scopedConsumptions = plotScopedMetricsFilter.applyUserScope(
                query.userId(),
                consumptions,
                consumption -> consumption.getPlotId().value()
        );
        return Result.success(scopedConsumptions);
    }

    @Override
    @Transactional(readOnly = true)
    public Result<WaterConsumption, ApplicationError> handle(GetWaterConsumptionByIdQuery query) {
        String id = query.id();
        if (id == null || id.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "water consumption id is required"));
        }
        var consumptionOptional = repository.findById(id);
        if (consumptionOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("WaterConsumption", id));
        }
        return Result.success(consumptionOptional.get());
    }
}
