package org.example.agrotrack.dashboard.interfaces.rest.transform;

import org.example.agrotrack.dashboard.domain.model.aggregates.LossSummary;
import org.example.agrotrack.dashboard.interfaces.rest.resource.LossSummaryResource;

public final class LossSummaryResourceAssembler {

    private LossSummaryResourceAssembler() {
    }

    public static LossSummaryResource toResource(LossSummary summary) {
        return new LossSummaryResource(
                summary.getId(),
                summary.getPlotId().value(),
                summary.getLossPercentage().value(),
                summary.getCause().value(),
                summary.getSeason().value(),
                summary.getCalculatedAt()
        );
    }
}
