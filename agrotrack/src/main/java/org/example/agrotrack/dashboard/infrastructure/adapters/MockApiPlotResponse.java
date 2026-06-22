package org.example.agrotrack.dashboard.infrastructure.adapters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
record MockApiPlotResponse(
        String id,
        @JsonProperty("user_id") String userId,
        String name,
        String status
) {
}
