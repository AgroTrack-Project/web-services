package org.example.agrotrack.soilmonitoring.interfaces.rest.transform;

import org.example.agrotrack.soilmonitoring.domain.model.queries.ListSoilRecordsQuery;

public final class ListSoilRecordsQueryFromRequestAssembler {

    private ListSoilRecordsQueryFromRequestAssembler() {
    }

    public static ListSoilRecordsQuery toQueryFromRequest(String plotId) {
        return new ListSoilRecordsQuery(plotId);
    }
}