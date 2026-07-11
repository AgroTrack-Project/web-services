package org.example.agrotrack.soilmonitoring.domain.model.commands;

import java.time.Instant;

public record UpdateIrrigationRecommendationCommand(
        String id,
        String status,
        Instant respondedAt
) {
}