package org.example.agrotrack.dashboard.interfaces.transform;

import org.example.agrotrack.dashboard.domain.model.WaterConsumption;
import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.entities.WaterConsumptionEntity;
import org.springframework.stereotype.Component;

@Component
public class WaterConsumptionEntityAssembler {

    public WaterConsumption toDomain(WaterConsumptionEntity entity) {
        return WaterConsumption.restore(
                entity.getId(),
                entity.getPlotId(),
                entity.getTotalLiters(),
                entity.getSeason(),
                entity.getCalculatedAt()
        );
    }
}
