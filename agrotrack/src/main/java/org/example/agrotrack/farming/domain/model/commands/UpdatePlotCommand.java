package org.example.agrotrack.farming.domain.model.commands;

public record UpdatePlotCommand(
        String id,
        String name,
        String location,
        Double sizeHectares
) {
}
