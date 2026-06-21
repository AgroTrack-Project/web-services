package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CropResource(
        String id,
        @JsonProperty("plot_id") String plotId,
        String type,
        @JsonProperty("sowing_date") LocalDate sowingDate,
        @JsonProperty("harvest_date") LocalDate harvestDate,
        String status
) {
}
