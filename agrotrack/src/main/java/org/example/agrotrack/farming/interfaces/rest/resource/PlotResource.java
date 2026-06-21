package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PlotResource(
        Long id,
        @JsonProperty("user_id") String userId,
        String name,
        String location,
        @JsonProperty("size_hectares") Double sizeHectares,
        String status,
        @JsonProperty("created_at") LocalDateTime createdAt
) {
}
