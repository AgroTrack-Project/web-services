package org.example.agrotrack.soilmonitoring.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateIrrigationRecommendationResource(
        String status,
        @JsonProperty("responded_at") Instant respondedAt
) {
}