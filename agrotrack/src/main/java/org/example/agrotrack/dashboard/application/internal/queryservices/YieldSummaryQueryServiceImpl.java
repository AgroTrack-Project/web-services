package org.example.agrotrack.dashboard.application.internal.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.application.internal.PlotScopedMetricsFilter;
import org.example.agrotrack.dashboard.application.queryservices.YieldSummaryQueryService;
import org.example.agrotrack.dashboard.domain.model.aggregates.YieldSummary;
import org.example.agrotrack.dashboard.domain.model.queries.GetYieldSummaryByIdQuery;
import org.example.agrotrack.dashboard.domain.model.queries.ListYieldSummariesQuery;
import org.example.agrotrack.dashboard.domain.repositories.YieldSummaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class YieldSummaryQueryServiceImpl implements YieldSummaryQueryService {

    private final YieldSummaryRepository repository;
    private final PlotScopedMetricsFilter plotScopedMetricsFilter;

    public YieldSummaryQueryServiceImpl(
            YieldSummaryRepository repository,
            PlotScopedMetricsFilter plotScopedMetricsFilter
    ) {
        this.repository = repository;
        this.plotScopedMetricsFilter = plotScopedMetricsFilter;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<List<YieldSummary>, ApplicationError> handle(ListYieldSummariesQuery query) {
        List<YieldSummary> summaries = repository.findAll();
        List<YieldSummary> scopedSummaries = plotScopedMetricsFilter.applyUserScope(
                query.userId(),
                summaries,
                summary -> summary.getPlotId().value()
        );
        return Result.success(scopedSummaries);
    }

    @Override
    @Transactional(readOnly = true)
    public Result<YieldSummary, ApplicationError> handle(GetYieldSummaryByIdQuery query) {
        String id = query.id();
        if (id == null || id.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "yield summary id is required"));
        }
        var summaryOptional = repository.findById(id);
        if (summaryOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("YieldSummary", id));
        }
        return Result.success(summaryOptional.get());
    }
}
