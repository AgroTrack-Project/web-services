package org.example.agrotrack.alerts.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record AlertResource(
        String id,
        String city,
        String title,
        String description,
        String urgency,
        @JsonProperty("generated_at") Instant generatedAt
) {}
