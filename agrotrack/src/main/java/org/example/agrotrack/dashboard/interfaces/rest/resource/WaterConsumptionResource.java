package org.example.agrotrack.dashboard.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record WaterConsumptionResource(
        String id,
        @JsonProperty("plot_id") String plotId,
        @JsonProperty("total_liters") double totalLiters,
        String season,
        @JsonProperty("calculated_at") Instant calculatedAt
) {
}
