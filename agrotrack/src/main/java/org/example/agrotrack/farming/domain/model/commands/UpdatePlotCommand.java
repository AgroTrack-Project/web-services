package org.example.agrotrack.farming.domain.model.commands;

/**
 * Requests updating the descriptive fields of an existing plot. Ownership ({@code userId})
 * and status are not editable through this command.
 */
public record UpdatePlotCommand(
        String id,
        String name,
        String location,
        Double sizeHectares
) {
}
