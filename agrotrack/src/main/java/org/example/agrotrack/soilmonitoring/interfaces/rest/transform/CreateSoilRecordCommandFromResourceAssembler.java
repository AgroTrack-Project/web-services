package org.example.agrotrack.soilmonitoring.interfaces.rest.transform;

import org.example.agrotrack.soilmonitoring.domain.model.commands.CreateSoilRecordCommand;
import org.example.agrotrack.soilmonitoring.interfaces.rest.resource.CreateSoilRecordResource;

public final class CreateSoilRecordCommandFromResourceAssembler {

    private CreateSoilRecordCommandFromResourceAssembler() {
    }

    public static CreateSoilRecordCommand toCommandFromResource(CreateSoilRecordResource resource) {
        return new CreateSoilRecordCommand(
                resource.plotId(),
                resource.humidity(),
                resource.temperature(),
                resource.recordedAt()
        );
    }
}