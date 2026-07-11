package org.example.agrotrack.support.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record SupportTicketResource(
        String id,
        @JsonProperty("user_id") String userId,
        String subject,
        String message,
        String status,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("responded_at") Instant respondedAt
) {
}
