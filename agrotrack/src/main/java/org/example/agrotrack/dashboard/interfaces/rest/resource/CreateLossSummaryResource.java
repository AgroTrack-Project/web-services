package org.example.agrotrack.dashboard.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateLossSummaryResource(
        @NotBlank @JsonProperty("plot_id") String plotId,
        @PositiveOrZero @JsonProperty("loss_percentage") double lossPercentage,
        @NotBlank String cause,
        @NotBlank String season
) {
}
