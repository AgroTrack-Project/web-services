package org.example.agrotrack.dashboard.interfaces.transform;

import org.example.agrotrack.dashboard.domain.model.LossSummary;
import org.example.agrotrack.dashboard.interfaces.resources.LossSummaryResource;
import org.springframework.stereotype.Component;

@Component
public class LossSummaryResourceAssembler {

    public LossSummaryResource toResource(LossSummary summary) {
        return new LossSummaryResource(
                summary.getId(),
                summary.getPlotId(),
                summary.getLossPercentage(),
                summary.getCause(),
                summary.getSeason(),
                summary.getCalculatedAt()
        );
    }
}
