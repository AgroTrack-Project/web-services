package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Response body representing a crop. {@code status} is serialized as the raw
 * {@link org.example.agrotrack.farming.domain.model.valueobjects.CropStatus} enum name.
 */
public record CropResource(
        String id,
        @JsonProperty("plot_id") String plotId,
        String type,
        @JsonProperty("sowing_date") LocalDate sowingDate,
        @JsonProperty("harvest_date") LocalDate harvestDate,
        String status
) {
}
