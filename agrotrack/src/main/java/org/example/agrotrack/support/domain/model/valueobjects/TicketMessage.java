package org.example.agrotrack.support.domain.model.valueobjects;

public record TicketMessage(String value) {

    private static final int MAX_LENGTH = 5000;

    public TicketMessage {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("support.ticket.error.message-blank");
        }
        value = value.trim();
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("support.ticket.error.message-too-long");
        }
    }
}
