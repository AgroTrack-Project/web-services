package org.example.agrotrack.farming.domain.model.commands;

import java.time.LocalDate;

public record UpdateCropCommand(
        String id,
        String type,
        LocalDate sowingDate,
        LocalDate harvestDate
) {
}
