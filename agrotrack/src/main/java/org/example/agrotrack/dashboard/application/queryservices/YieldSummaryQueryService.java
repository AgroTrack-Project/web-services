package org.example.agrotrack.dashboard.application.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.domain.model.aggregates.YieldSummary;
import org.example.agrotrack.dashboard.domain.model.queries.GetYieldSummaryByIdQuery;
import org.example.agrotrack.dashboard.domain.model.queries.ListYieldSummariesQuery;

import java.util.List;

public interface YieldSummaryQueryService {

    Result<List<YieldSummary>, ApplicationError> handle(ListYieldSummariesQuery query);

    Result<YieldSummary, ApplicationError> handle(GetYieldSummaryByIdQuery query);
}
