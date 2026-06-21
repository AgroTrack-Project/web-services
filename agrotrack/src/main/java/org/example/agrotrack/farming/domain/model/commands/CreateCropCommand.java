package org.example.agrotrack.farming.domain.model.commands;

import java.time.LocalDate;

public record CreateCropCommand(
        Long plotId,
        String type,
        LocalDate sowingDate,
        LocalDate harvestDate
) {
}
