package org.example.agrotrack.dashboard.domain.model.commands;

public record CreateLossSummaryCommand(
        String plotId,
        double lossPercentage,
        String cause,
        String season
) {
}
