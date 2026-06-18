package org.example.agrotrack.support.domain.model.commands;

public record CreateSupportTicketCommand(
        String userId,
        String subject,
        String message
) {
}
