package org.example.agrotrack.dashboard.interfaces.rest.transform;

import org.example.agrotrack.dashboard.domain.model.aggregates.YieldSummary;
import org.example.agrotrack.dashboard.interfaces.rest.resource.YieldSummaryResource;

public final class YieldSummaryResourceAssembler {

    private YieldSummaryResourceAssembler() {
    }

    public static YieldSummaryResource toResource(YieldSummary summary) {
        return new YieldSummaryResource(
                summary.getId(),
                summary.getPlotId().value(),
                summary.getYieldPerHectare().value(),
                summary.getSeason().value(),
                summary.getCalculatedAt()
        );
    }
}
