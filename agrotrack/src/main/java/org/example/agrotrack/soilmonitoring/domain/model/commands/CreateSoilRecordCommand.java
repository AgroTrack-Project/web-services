package org.example.agrotrack.soilmonitoring.domain.model.commands;

import java.time.Instant;

public record CreateSoilRecordCommand(
        String plotId,
        Double humidity,
        Double temperature,
        Instant recordedAt
) {
}