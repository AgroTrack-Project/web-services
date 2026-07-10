package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * Request body for {@code PUT /crops/{id}/harvest}. Unlike the optional {@code harvest_date}
 * on creation/update, this one is required — it's the actual date being recorded.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HarvestCropResource(
        @NotNull @JsonProperty("harvest_date") LocalDate harvestDate
) {
}
