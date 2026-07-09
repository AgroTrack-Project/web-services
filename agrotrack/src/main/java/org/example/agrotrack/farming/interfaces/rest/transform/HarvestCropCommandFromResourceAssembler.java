package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.commands.HarvestCropCommand;
import org.example.agrotrack.farming.interfaces.rest.resource.HarvestCropResource;

public final class HarvestCropCommandFromResourceAssembler {

    private HarvestCropCommandFromResourceAssembler() {
    }

    public static HarvestCropCommand toCommandFromResource(String id, HarvestCropResource resource) {
        return new HarvestCropCommand(id, resource.harvestDate());
    }
}
