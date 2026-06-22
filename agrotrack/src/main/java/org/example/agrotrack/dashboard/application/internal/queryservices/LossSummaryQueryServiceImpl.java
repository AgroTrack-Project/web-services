package org.example.agrotrack.dashboard.application.internal.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.application.internal.PlotScopedMetricsFilter;
import org.example.agrotrack.dashboard.application.queryservices.LossSummaryQueryService;
import org.example.agrotrack.dashboard.domain.model.aggregates.LossSummary;
import org.example.agrotrack.dashboard.domain.model.queries.GetLossSummaryByIdQuery;
import org.example.agrotrack.dashboard.domain.model.queries.ListLossSummariesQuery;
import org.example.agrotrack.dashboard.domain.repositories.LossSummaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LossSummaryQueryServiceImpl implements LossSummaryQueryService {

    private final LossSummaryRepository repository;
    private final PlotScopedMetricsFilter plotScopedMetricsFilter;

    public LossSummaryQueryServiceImpl(
            LossSummaryRepository repository,
            PlotScopedMetricsFilter plotScopedMetricsFilter
    ) {
        this.repository = repository;
        this.plotScopedMetricsFilter = plotScopedMetricsFilter;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<List<LossSummary>, ApplicationError> handle(ListLossSummariesQuery query) {
        List<LossSummary> summaries = repository.findAll();
        List<LossSummary> scopedSummaries = plotScopedMetricsFilter.applyUserScope(
                query.userId(),
                summaries,
                summary -> summary.getPlotId().value()
        );
        return Result.success(scopedSummaries);
    }

    @Override
    @Transactional(readOnly = true)
    public Result<LossSummary, ApplicationError> handle(GetLossSummaryByIdQuery query) {
        String id = query.id();
        if (id == null || id.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "loss summary id is required"));
        }
        var summaryOptional = repository.findById(id);
        if (summaryOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("LossSummary", id));
        }
        return Result.success(summaryOptional.get());
    }
}
