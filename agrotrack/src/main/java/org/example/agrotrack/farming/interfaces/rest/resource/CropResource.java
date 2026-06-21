package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CropResource(
        Long id,
        @JsonProperty("plot_id") Long plotId,
        String type,
        @JsonProperty("sowing_date") LocalDate sowingDate,
        @JsonProperty("harvest_date") LocalDate harvestDate,
        String status
) {
}
