package org.example.agrotrack.soilmonitoring.application.internal.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.soilmonitoring.application.queryservices.IrrigationRecommendationQueryService;
import org.example.agrotrack.soilmonitoring.domain.model.aggregates.IrrigationRecommendation;
import org.example.agrotrack.soilmonitoring.domain.model.queries.GetIrrigationRecommendationByIdQuery;
import org.example.agrotrack.soilmonitoring.domain.model.queries.ListIrrigationRecommendationsQuery;
import org.example.agrotrack.soilmonitoring.domain.repositories.IrrigationRecommendationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class IrrigationRecommendationQueryServiceImpl implements IrrigationRecommendationQueryService {

    private final IrrigationRecommendationRepository repository;

    public IrrigationRecommendationQueryServiceImpl(IrrigationRecommendationRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<List<IrrigationRecommendation>, ApplicationError> handle(ListIrrigationRecommendationsQuery query) {
        String plotId = query.plotId();

        if (plotId != null && !plotId.isBlank()) {
            return Result.success(repository.findByPlotIdOrderByGeneratedAtDesc(plotId.trim()));
        }

        return Result.success(
                repository.findAll().stream()
                        .sorted(Comparator.comparing(IrrigationRecommendation::getGeneratedAt).reversed())
                        .toList()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Result<IrrigationRecommendation, ApplicationError> handle(GetIrrigationRecommendationByIdQuery query) {
        String id = query.id();

        if (id == null || id.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "irrigation recommendation id is required"));
        }

        var recommendationOptional = repository.findById(id);

        if (recommendationOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("IrrigationRecommendation", id));
        }

        return Result.success(recommendationOptional.get());
    }
}