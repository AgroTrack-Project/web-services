package org.example.agrotrack.support.interfaces.rest.transform;

import org.example.agrotrack.support.domain.model.queries.ListSupportTicketsQuery;

public final class ListSupportTicketsQueryFromRequestAssembler {

    private ListSupportTicketsQueryFromRequestAssembler() {
    }

    public static ListSupportTicketsQuery toQueryFromRequest(String userId) {
        return new ListSupportTicketsQuery(userId);
    }
}
