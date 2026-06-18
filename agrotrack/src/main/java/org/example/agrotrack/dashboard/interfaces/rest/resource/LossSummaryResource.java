package org.example.agrotrack.dashboard.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record LossSummaryResource(
        String id,
        @JsonProperty("plot_id") String plotId,
        @JsonProperty("loss_percentage") double lossPercentage,
        String cause,
        String season,
        @JsonProperty("calculated_at") Instant calculatedAt
) {
}
