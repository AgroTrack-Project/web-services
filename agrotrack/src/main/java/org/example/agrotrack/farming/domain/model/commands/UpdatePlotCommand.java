package org.example.agrotrack.farming.domain.model.commands;

public record UpdatePlotCommand(
        Long id,
        String name,
        String location,
        Double sizeHectares
) {
}
