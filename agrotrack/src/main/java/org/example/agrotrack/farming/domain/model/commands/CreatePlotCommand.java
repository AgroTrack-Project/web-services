package org.example.agrotrack.farming.domain.model.commands;

/**
 * Requests creation of a new plot for a given user. The plot is always created in
 * {@code ACTIVE} status; there is no way to create an inactive plot.
 */
public record CreatePlotCommand(
        String userId,
        String name,
        String location,
        Double sizeHectares
) {
}
