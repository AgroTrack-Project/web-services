package org.example.agrotrack.support.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateSupportTicketResource(
        @JsonProperty("user_id") String userId,
        String subject,
        String message
) {
}
