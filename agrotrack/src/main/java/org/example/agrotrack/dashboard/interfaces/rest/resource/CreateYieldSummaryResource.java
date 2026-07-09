package org.example.agrotrack.dashboard.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateYieldSummaryResource(
        @NotBlank @JsonProperty("plot_id") String plotId,
        @Positive @JsonProperty("yield_per_hectare") double yieldPerHectare,
        @NotBlank String season
) {
}
