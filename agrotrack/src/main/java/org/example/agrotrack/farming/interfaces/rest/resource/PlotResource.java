package org.example.agrotrack.farming.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * Response body representing a plot. {@code status} is serialized as the raw
 * {@link org.example.agrotrack.farming.domain.model.valueobjects.PlotStatus} enum name.
 */
public record PlotResource(
        String id,
        @JsonProperty("user_id") String userId,
        String name,
        String location,
        @JsonProperty("size_hectares") Double sizeHectares,
        String status,
        @JsonProperty("created_at") LocalDateTime createdAt
) {
}
