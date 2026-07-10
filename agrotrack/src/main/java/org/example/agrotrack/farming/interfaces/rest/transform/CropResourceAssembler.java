package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.interfaces.rest.resource.CropResource;

/**
 * Maps a {@code Crop} domain aggregate to its outbound {@code CropResource} representation.
 */
public final class CropResourceAssembler {

    private CropResourceAssembler() {
    }

    public static CropResource toResource(Crop crop) {
        return new CropResource(
                crop.getId(),
                crop.getPlotId(),
                crop.getType(),
                crop.getSowingDate(),
                crop.getHarvestDate(),
                // Serialized as the raw enum name rather than a display label.
                crop.getStatus().name()
        );
    }
}
