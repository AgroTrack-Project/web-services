package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Request body for {@code PUT /plots/{id}}. Does not include {@code userId} or {@code status} —
 * plot ownership and status are not editable through this endpoint.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdatePlotResource(
        @NotBlank @Size(max = 100) String name,
        @NotBlank String location,
        @NotNull @Positive @JsonProperty("size_hectares") Double sizeHectares
) {
}
