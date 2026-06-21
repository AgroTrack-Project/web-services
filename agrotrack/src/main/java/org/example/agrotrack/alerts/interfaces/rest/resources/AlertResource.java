package org.example.agrotrack.alerts.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * REST resource representing a weather alert returned to clients.
 *
 * @param id unique identifier of the alert
 * @param city city associated with the alert
 * @param title alert title
 * @param description alert description
 * @param urgency alert urgency level
 * @param generatedAt timestamp when the alert was generated
 */
public record AlertResource(
        String id,
        String city,
        String title,
        String description,
        String urgency,
        @JsonProperty("generated_at") Instant generatedAt
) {}