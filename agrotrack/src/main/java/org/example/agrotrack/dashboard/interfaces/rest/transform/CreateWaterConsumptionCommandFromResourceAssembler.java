package org.example.agrotrack.dashboard.interfaces.rest.transform;

import org.example.agrotrack.dashboard.domain.model.commands.CreateWaterConsumptionCommand;
import org.example.agrotrack.dashboard.interfaces.rest.resource.CreateWaterConsumptionResource;

public final class CreateWaterConsumptionCommandFromResourceAssembler {

    private CreateWaterConsumptionCommandFromResourceAssembler() {
    }

    public static CreateWaterConsumptionCommand toCommandFromResource(CreateWaterConsumptionResource resource) {
        return new CreateWaterConsumptionCommand(resource.plotId(), resource.totalLiters(), resource.season());
    }
}
