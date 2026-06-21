package org.example.agrotrack.farming.domain.model.commands;

public record CreatePlotCommand(
        String userId,
        String name,
        String location,
        Double sizeHectares
) {
}
