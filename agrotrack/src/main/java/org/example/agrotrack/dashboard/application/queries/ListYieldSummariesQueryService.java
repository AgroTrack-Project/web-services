package org.example.agrotrack.dashboard.application.queries;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.domain.model.YieldSummary;
import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.repositories.JpaYieldSummaryRepository;
import org.example.agrotrack.dashboard.interfaces.transform.YieldSummaryEntityAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListYieldSummariesQueryService {

    private final JpaYieldSummaryRepository repository;
    private final YieldSummaryEntityAssembler entityAssembler;
    private final PlotScopedMetricsFilter plotScopedMetricsFilter;

    @Transactional(readOnly = true)
    public Result<List<YieldSummary>, ApplicationError> findAll(String userId) {
        List<YieldSummary> summaries = repository.findAll().stream()
                .map(entityAssembler::toDomain)
                .toList();
        List<YieldSummary> scopedSummaries = plotScopedMetricsFilter.applyUserScope(
                userId,
                summaries,
                YieldSummary::getPlotId
        );
        return Result.success(scopedSummaries);
    }
}
