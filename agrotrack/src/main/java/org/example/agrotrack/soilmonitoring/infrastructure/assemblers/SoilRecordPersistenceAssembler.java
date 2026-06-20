package org.example.agrotrack.soilmonitoring.infrastructure.assemblers;

import org.example.agrotrack.soilmonitoring.domain.model.aggregates.SoilRecord;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.Humidity;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.PlotId;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.Temperature;
import org.example.agrotrack.soilmonitoring.infrastructure.entities.SoilRecordPersistenceEntity;

public final class SoilRecordPersistenceAssembler {

    private SoilRecordPersistenceAssembler() {
    }

    public static SoilRecord toDomainFromPersistence(SoilRecordPersistenceEntity entity) {
        return SoilRecord.restore(
                entity.getId(),
                new PlotId(entity.getPlotId()),
                new Humidity(entity.getHumidity()),
                new Temperature(entity.getTemperature()),
                entity.getRecordedAt()
        );
    }

    public static SoilRecordPersistenceEntity toPersistenceFromDomain(SoilRecord soilRecord) {
        SoilRecordPersistenceEntity entity = new SoilRecordPersistenceEntity();
        entity.setPlotId(soilRecord.getPlotId().value());
        entity.setHumidity(soilRecord.getHumidity().value());
        entity.setTemperature(soilRecord.getTemperature().value());
        entity.setRecordedAt(soilRecord.getRecordedAt());
        return entity;
    }
}