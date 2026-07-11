package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Request body for {@code POST /crops}. Fields use snake_case JSON keys to match the frontend's
 * naming convention; {@code harvest_date} is optional (a planned date, not an actual harvest).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateCropResource(
        @NotBlank @JsonProperty("plot_id") String plotId,
        @NotBlank @Size(max = 100) String type,
        @NotNull @JsonProperty("sowing_date") LocalDate sowingDate,
        @JsonProperty("harvest_date") LocalDate harvestDate
) {
}
