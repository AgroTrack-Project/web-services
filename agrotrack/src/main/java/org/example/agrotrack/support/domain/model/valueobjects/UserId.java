package org.example.agrotrack.support.domain.model.valueobjects;

public record UserId(String value) {

    public UserId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("support.ticket.error.user-id-blank");
        }
        value = value.trim();
    }
}
