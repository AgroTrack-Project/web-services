package org.example.agrotrack.dashboard.application.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.dashboard.domain.model.aggregates.WaterConsumption;
import org.example.agrotrack.dashboard.domain.model.queries.GetWaterConsumptionByIdQuery;
import org.example.agrotrack.dashboard.domain.model.queries.ListWaterConsumptionsQuery;

import java.util.List;

public interface WaterConsumptionQueryService {

    Result<List<WaterConsumption>, ApplicationError> handle(ListWaterConsumptionsQuery query);

    Result<WaterConsumption, ApplicationError> handle(GetWaterConsumptionByIdQuery query);
}
