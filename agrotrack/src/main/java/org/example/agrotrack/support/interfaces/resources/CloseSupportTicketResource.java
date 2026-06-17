package org.example.agrotrack.support.interfaces.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CloseSupportTicketResource(
        String status
) {
}
