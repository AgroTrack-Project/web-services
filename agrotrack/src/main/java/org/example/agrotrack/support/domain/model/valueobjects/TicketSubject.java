package org.example.agrotrack.support.domain.model.valueobjects;

public record TicketSubject(String value) {

    private static final int MAX_LENGTH = 200;

    public TicketSubject {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("support.ticket.error.subject-blank");
        }
        value = value.trim();
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("support.ticket.error.subject-too-long");
        }
    }
}
