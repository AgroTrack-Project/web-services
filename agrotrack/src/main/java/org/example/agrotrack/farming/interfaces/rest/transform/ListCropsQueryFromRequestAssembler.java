package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.queries.ListCropsQuery;

public final class ListCropsQueryFromRequestAssembler {

    private ListCropsQueryFromRequestAssembler() {
    }

    public static ListCropsQuery toQueryFromRequest(Long plotId) {
        return new ListCropsQuery(plotId);
    }
}
