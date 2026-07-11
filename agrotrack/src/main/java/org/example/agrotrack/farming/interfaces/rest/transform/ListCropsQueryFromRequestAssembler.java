package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.queries.ListCropsQuery;

/**
 * Maps the {@code plotId} request parameter to the domain-level {@code ListCropsQuery}.
 */
public final class ListCropsQueryFromRequestAssembler {

    private ListCropsQueryFromRequestAssembler() {
    }

    public static ListCropsQuery toQueryFromRequest(String plotId) {
        return new ListCropsQuery(plotId);
    }
}
