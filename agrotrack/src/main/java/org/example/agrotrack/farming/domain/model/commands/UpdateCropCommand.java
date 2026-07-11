package org.example.agrotrack.farming.domain.model.commands;

import java.time.LocalDate;

/**
 * Requests updating the descriptive fields of an existing crop. This does not change the
 * crop's status — use {@link HarvestCropCommand} to record an actual harvest.
 */
public record UpdateCropCommand(
        String id,
        String type,
        LocalDate sowingDate,
        LocalDate harvestDate
) {
}
