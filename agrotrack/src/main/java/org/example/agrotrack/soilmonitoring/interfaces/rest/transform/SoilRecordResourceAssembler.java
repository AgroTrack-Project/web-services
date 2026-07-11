package org.example.agrotrack.soilmonitoring.interfaces.rest.transform;

import org.example.agrotrack.soilmonitoring.domain.model.aggregates.SoilRecord;
import org.example.agrotrack.soilmonitoring.interfaces.rest.resource.SoilRecordResource;

public final class SoilRecordResourceAssembler {

    private SoilRecordResourceAssembler() {
    }

    public static SoilRecordResource toResource(SoilRecord soilRecord) {
        return new SoilRecordResource(
                soilRecord.getId(),
                soilRecord.getPlotId().value(),
                soilRecord.getHumidity().value(),
                soilRecord.getTemperature().value(),
                soilRecord.getRecordedAt()
        );
    }
}