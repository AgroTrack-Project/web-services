package org.example.agrotrack.farming.domain.model.commands;

import java.time.LocalDate;

/**
 * Requests recording the actual harvest for the crop identified by {@code id}. Handling this
 * command also triggers downstream dashboard metrics generation (yield, water, loss) for the
 * crop's plot; a crop can only be harvested once.
 */
public record HarvestCropCommand(
        String id,
        LocalDate harvestDate
) {
}
