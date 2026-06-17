package org.example.agrotrack.dashboard.interfaces.transform;

import org.example.agrotrack.dashboard.domain.model.WaterConsumption;
import org.example.agrotrack.dashboard.interfaces.resources.WaterConsumptionResource;
import org.springframework.stereotype.Component;

@Component
public class WaterConsumptionResourceAssembler {

    public WaterConsumptionResource toResource(WaterConsumption consumption) {
        return new WaterConsumptionResource(
                consumption.getId(),
                consumption.getPlotId(),
                consumption.getTotalLiters(),
                consumption.getSeason(),
                consumption.getCalculatedAt()
        );
    }
}
