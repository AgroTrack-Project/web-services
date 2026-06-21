package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.commands.UpdateCropCommand;
import org.example.agrotrack.farming.interfaces.rest.resource.UpdateCropResource;

public final class UpdateCropCommandFromResourceAssembler {

    private UpdateCropCommandFromResourceAssembler() {
    }

    public static UpdateCropCommand toCommandFromResource(String id, UpdateCropResource resource) {
        return new UpdateCropCommand(
                id,
                resource.type(),
                resource.sowingDate(),
                resource.harvestDate()
        );
    }
}
