package org.example.agrotrack.farming.domain.model.commands;

import java.time.LocalDate;

/**
 * Requests sowing a new crop on an existing plot. {@code harvestDate} is optional and,
 * when supplied, represents a planned harvest date rather than an actual one — the crop
 * is created in {@code ACTIVE} status regardless.
 */
public record CreateCropCommand(
        String plotId,
        String type,
        LocalDate sowingDate,
        LocalDate harvestDate
) {
}
