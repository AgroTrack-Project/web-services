package org.example.agrotrack.dashboard.domain.model.commands;

public record CreateWaterConsumptionCommand(
        String plotId,
        double totalLiters,
        String season
) {
}
