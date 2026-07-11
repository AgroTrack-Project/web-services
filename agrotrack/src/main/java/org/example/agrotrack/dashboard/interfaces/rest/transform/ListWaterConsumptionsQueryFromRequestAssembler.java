package org.example.agrotrack.dashboard.interfaces.rest.transform;

import org.example.agrotrack.dashboard.domain.model.queries.ListWaterConsumptionsQuery;

public final class ListWaterConsumptionsQueryFromRequestAssembler {

    private ListWaterConsumptionsQueryFromRequestAssembler() {
    }

    public static ListWaterConsumptionsQuery toQueryFromRequest(String userId) {
        return new ListWaterConsumptionsQuery(userId);
    }
}
