package org.example.agrotrack.soilmonitoring.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record IrrigationRecommendationResource(
        String id,
        @JsonProperty("plot_id") String plotId,
        @JsonProperty("soil_record_id") String soilRecordId,
        String message,
        String urgency,
        String status,
        @JsonProperty("generated_at") Instant generatedAt,
        @JsonProperty("responded_at") Instant respondedAt
) {
}