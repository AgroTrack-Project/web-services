package org.example.agrotrack.dashboard.interfaces.rest.transform;

import org.example.agrotrack.dashboard.domain.model.queries.ListYieldSummariesQuery;

public final class ListYieldSummariesQueryFromRequestAssembler {

    private ListYieldSummariesQueryFromRequestAssembler() {
    }

    public static ListYieldSummariesQuery toQueryFromRequest(String userId) {
        return new ListYieldSummariesQuery(userId);
    }
}
