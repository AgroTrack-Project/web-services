package org.example.agrotrack.soilmonitoring.interfaces.acl;

import java.time.Instant;

public record SoilRecordSummary(
        String id,
        double humidity,
        double temperature,
        String status,
        Instant recordedAt
) {
}
