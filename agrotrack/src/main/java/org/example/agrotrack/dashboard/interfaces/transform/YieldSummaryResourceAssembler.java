package org.example.agrotrack.dashboard.interfaces.transform;

import org.example.agrotrack.dashboard.domain.model.YieldSummary;
import org.example.agrotrack.dashboard.interfaces.resources.YieldSummaryResource;
import org.springframework.stereotype.Component;

@Component
public class YieldSummaryResourceAssembler {

    public YieldSummaryResource toResource(YieldSummary summary) {
        return new YieldSummaryResource(
                summary.getId(),
                summary.getPlotId(),
                summary.getYieldPerHectare(),
                summary.getSeason(),
                summary.getCalculatedAt()
        );
    }
}
