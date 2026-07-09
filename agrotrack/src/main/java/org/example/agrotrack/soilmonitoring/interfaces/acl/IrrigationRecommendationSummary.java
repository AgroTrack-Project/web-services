package org.example.agrotrack.soilmonitoring.interfaces.acl;

import java.time.Instant;

public record IrrigationRecommendationSummary(
        String id,
        String urgency,
        String status,
        Instant generatedAt
) {
}
