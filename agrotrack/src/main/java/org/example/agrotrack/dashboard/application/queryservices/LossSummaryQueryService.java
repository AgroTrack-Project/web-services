package org.example.agrotrack.dashboard.application.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.domain.model.aggregates.LossSummary;
import org.example.agrotrack.dashboard.domain.model.queries.GetLossSummaryByIdQuery;
import org.example.agrotrack.dashboard.domain.model.queries.ListLossSummariesQuery;

import java.util.List;

public interface LossSummaryQueryService {

    Result<List<LossSummary>, ApplicationError> handle(ListLossSummariesQuery query);

    Result<LossSummary, ApplicationError> handle(GetLossSummaryByIdQuery query);
}
