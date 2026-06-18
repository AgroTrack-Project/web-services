package org.example.agrotrack.dashboard.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record YieldSummaryResource(
        String id,
        @JsonProperty("plot_id") String plotId,
        @JsonProperty("yield_per_hectare") double yieldPerHectare,
        String season,
        @JsonProperty("calculated_at") Instant calculatedAt
) {
}
