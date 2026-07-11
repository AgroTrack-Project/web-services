package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Request body for {@code PUT /crops/{id}}. Does not include {@code plotId} or {@code status} —
 * a crop cannot be reassigned to a different plot, and status changes only happen through the
 * dedicated harvest endpoint.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateCropResource(
        @NotBlank @Size(max = 100) String type,
        @NotNull @JsonProperty("sowing_date") LocalDate sowingDate,
        @JsonProperty("harvest_date") LocalDate harvestDate
) {
}
