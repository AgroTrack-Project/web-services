package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HarvestCropResource(
        @NotNull @JsonProperty("harvest_date") LocalDate harvestDate
) {
}
