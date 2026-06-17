package org.example.agrotrack.dashboard.application.queries;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.domain.model.LossSummary;
import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.repositories.JpaLossSummaryRepository;
import org.example.agrotrack.dashboard.interfaces.transform.LossSummaryEntityAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListLossSummariesQueryService {

    private final JpaLossSummaryRepository repository;
    private final LossSummaryEntityAssembler entityAssembler;
    private final PlotScopedMetricsFilter plotScopedMetricsFilter;

    @Transactional(readOnly = true)
    public Result<List<LossSummary>, ApplicationError> findAll(String userId) {
        List<LossSummary> summaries = repository.findAll().stream()
                .map(entityAssembler::toDomain)
                .toList();
        List<LossSummary> scopedSummaries = plotScopedMetricsFilter.applyUserScope(
                userId,
                summaries,
                LossSummary::getPlotId
        );
        return Result.success(scopedSummaries);
    }
}
