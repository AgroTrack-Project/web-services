package org.example.agrotrack.dashboard.interfaces.rest.transform;

import org.example.agrotrack.dashboard.domain.model.queries.ListLossSummariesQuery;

public final class ListLossSummariesQueryFromRequestAssembler {

    private ListLossSummariesQueryFromRequestAssembler() {
    }

    public static ListLossSummariesQuery toQueryFromRequest(String userId) {
        return new ListLossSummariesQuery(userId);
    }
}
