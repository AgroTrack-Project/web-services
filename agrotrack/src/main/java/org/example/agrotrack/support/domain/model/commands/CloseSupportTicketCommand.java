package org.example.agrotrack.support.domain.model.commands;

public record CloseSupportTicketCommand(
        String ticketId,
        String requestedStatus
) {
}
