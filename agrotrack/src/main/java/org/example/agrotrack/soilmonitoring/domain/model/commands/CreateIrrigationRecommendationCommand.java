package org.example.agrotrack.soilmonitoring.domain.model.commands;

import java.time.Instant;

public record CreateIrrigationRecommendationCommand(
        String plotId,
        String soilRecordId,
        String message,
        String urgency,
        String status,
        Instant generatedAt,
        Instant respondedAt
) {
}