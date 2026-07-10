package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.commands.HarvestCropCommand;
import org.example.agrotrack.farming.interfaces.rest.resource.HarvestCropResource;

/**
 * Maps an incoming {@code HarvestCropResource} to the domain-level {@code HarvestCropCommand}.
 * The crop id comes from the path variable rather than the request body.
 */
public final class HarvestCropCommandFromResourceAssembler {

    private HarvestCropCommandFromResourceAssembler() {
    }

    public static HarvestCropCommand toCommandFromResource(String id, HarvestCropResource resource) {
        return new HarvestCropCommand(id, resource.harvestDate());
    }
}
