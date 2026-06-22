package org.example.agrotrack.dashboard.interfaces.rest.transform;

import org.example.agrotrack.dashboard.domain.model.aggregates.WaterConsumption;
import org.example.agrotrack.dashboard.interfaces.rest.resource.WaterConsumptionResource;

public final class WaterConsumptionResourceAssembler {

    private WaterConsumptionResourceAssembler() {
    }

    public static WaterConsumptionResource toResource(WaterConsumption consumption) {
        return new WaterConsumptionResource(
                consumption.getId(),
                consumption.getPlotId().value(),
                consumption.getTotalLiters().value(),
                consumption.getSeason().value(),
                consumption.getCalculatedAt()
        );
    }
}
