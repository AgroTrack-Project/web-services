package org.example.agrotrack.soilmonitoring.domain.model.commands;

public record DeleteSoilMonitoringDataByPlotIdCommand(
        String plotId
) {
}