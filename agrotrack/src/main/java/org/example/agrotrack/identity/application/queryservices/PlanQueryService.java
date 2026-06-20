package org.example.agrotrack.identity.application.queryservices;

import org.example.agrotrack.identity.domain.model.aggregates.Plan;
import org.example.agrotrack.identity.domain.model.queries.ListPlansQuery;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

import java.util.List;

public interface PlanQueryService {
    Result<List<Plan>, ApplicationError> handle(ListPlansQuery query);
}
