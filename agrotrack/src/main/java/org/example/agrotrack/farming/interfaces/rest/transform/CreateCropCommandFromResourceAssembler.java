package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.commands.CreateCropCommand;
import org.example.agrotrack.farming.interfaces.rest.resource.CreateCropResource;

public final class CreateCropCommandFromResourceAssembler {

    private CreateCropCommandFromResourceAssembler() {
    }

    public static CreateCropCommand toCommandFromResource(CreateCropResource resource) {
        return new CreateCropCommand(
                resource.plotId(),
                resource.type(),
                resource.sowingDate(),
                resource.harvestDate()
        );
    }
}
