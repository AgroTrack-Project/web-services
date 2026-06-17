package org.example.agrotrack.dashboard.interfaces.transform;

import org.example.agrotrack.dashboard.domain.model.LossSummary;
import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.entities.LossSummaryEntity;
import org.springframework.stereotype.Component;

@Component
public class LossSummaryEntityAssembler {

    public LossSummary toDomain(LossSummaryEntity entity) {
        return LossSummary.restore(
                entity.getId(),
                entity.getPlotId(),
                entity.getLossPercentage(),
                entity.getCause(),
                entity.getSeason(),
                entity.getCalculatedAt()
        );
    }
}
