package org.example.agrotrack.dashboard.domain.model.commands;

public record CreateYieldSummaryCommand(
        String plotId,
        double yieldPerHectare,
        String season
) {
}
