package org.example.agrotrack.soilmonitoring.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateSoilRecordResource(
        @JsonProperty("plot_id") String plotId,
        Double humidity,
        Double temperature,
        @JsonProperty("recorded_at") Instant recordedAt
) {
}