package org.example.agrotrack.farming.domain.model.commands;

import java.time.LocalDate;

public record HarvestCropCommand(
        String id,
        LocalDate harvestDate
) {
}
