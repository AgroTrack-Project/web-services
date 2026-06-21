package org.example.agrotrack.soilmonitoring.application.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.soilmonitoring.domain.model.aggregates.IrrigationRecommendation;
import org.example.agrotrack.soilmonitoring.domain.model.queries.ListIrrigationRecommendationsQuery;

import java.util.List;

public interface IrrigationRecommendationQueryService {

    Result<List<IrrigationRecommendation>, ApplicationError> handle(ListIrrigationRecommendationsQuery query);
}