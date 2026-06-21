package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.queries.ListPlotsQuery;

public final class ListPlotsQueryFromRequestAssembler {

    private ListPlotsQueryFromRequestAssembler() {
    }

    public static ListPlotsQuery toQueryFromRequest(String userId) {
        return new ListPlotsQuery(userId);
    }
}
