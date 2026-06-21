package org.example.agrotrack.farming.domain.model.commands;

import java.time.LocalDate;

public record UpdateCropCommand(
        Long id,
        String type,
        LocalDate sowingDate,
        LocalDate harvestDate
) {
}
