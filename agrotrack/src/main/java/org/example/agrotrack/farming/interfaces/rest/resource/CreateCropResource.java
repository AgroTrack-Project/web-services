package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateCropResource(
        @NotNull @Positive @JsonProperty("plot_id") Long plotId,
        @NotBlank @Size(max = 100) String type,
        @NotNull @JsonProperty("sowing_date") LocalDate sowingDate,
        @JsonProperty("harvest_date") LocalDate harvestDate
) {
}
