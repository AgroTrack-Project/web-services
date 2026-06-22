package org.example.agrotrack.dashboard.infrastructure.assemblers;

import org.example.agrotrack.dashboard.domain.model.aggregates.WaterConsumption;
import org.example.agrotrack.dashboard.domain.model.valueobjects.PlotId;
import org.example.agrotrack.dashboard.domain.model.valueobjects.Season;
import org.example.agrotrack.dashboard.domain.model.valueobjects.TotalLiters;
import org.example.agrotrack.dashboard.infrastructure.entities.WaterConsumptionPersistenceEntity;

public final class WaterConsumptionPersistenceAssembler {

    private WaterConsumptionPersistenceAssembler() {
    }

    public static WaterConsumption toDomainFromPersistence(WaterConsumptionPersistenceEntity entity) {
        return WaterConsumption.restore(
                entity.getId(),
                new PlotId(entity.getPlotId()),
                new TotalLiters(entity.getTotalLiters()),
                new Season(entity.getSeason()),
                entity.getCalculatedAt()
        );
    }

    public static WaterConsumptionPersistenceEntity toPersistenceFromDomain(WaterConsumption consumption) {
        WaterConsumptionPersistenceEntity entity = new WaterConsumptionPersistenceEntity();
        entity.setPlotId(consumption.getPlotId().value());
        entity.setTotalLiters(consumption.getTotalLiters().value());
        entity.setSeason(consumption.getSeason().value());
        entity.setCalculatedAt(consumption.getCalculatedAt());
        return entity;
    }
}
