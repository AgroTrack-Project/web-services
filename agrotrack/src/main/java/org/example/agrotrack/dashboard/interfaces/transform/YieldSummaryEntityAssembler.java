package org.example.agrotrack.dashboard.interfaces.transform;

import org.example.agrotrack.dashboard.domain.model.YieldSummary;
import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.entities.YieldSummaryEntity;
import org.springframework.stereotype.Component;

@Component
public class YieldSummaryEntityAssembler {

    public YieldSummary toDomain(YieldSummaryEntity entity) {
        return YieldSummary.restore(
                entity.getId(),
                entity.getPlotId(),
                entity.getYieldPerHectare(),
                entity.getSeason(),
                entity.getCalculatedAt()
        );
    }
}
