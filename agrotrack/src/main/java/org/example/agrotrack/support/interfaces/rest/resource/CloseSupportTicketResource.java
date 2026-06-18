package org.example.agrotrack.support.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CloseSupportTicketResource(
        String status
) {
}
