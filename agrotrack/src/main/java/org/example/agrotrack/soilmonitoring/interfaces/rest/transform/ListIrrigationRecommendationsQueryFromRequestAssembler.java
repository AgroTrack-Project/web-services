package org.example.agrotrack.soilmonitoring.interfaces.rest.transform;

import org.example.agrotrack.soilmonitoring.domain.model.queries.ListIrrigationRecommendationsQuery;

public final class ListIrrigationRecommendationsQueryFromRequestAssembler {

    private ListIrrigationRecommendationsQueryFromRequestAssembler() {
    }

    public static ListIrrigationRecommendationsQuery toQueryFromRequest(String plotId) {
        return new ListIrrigationRecommendationsQuery(plotId);
    }
}