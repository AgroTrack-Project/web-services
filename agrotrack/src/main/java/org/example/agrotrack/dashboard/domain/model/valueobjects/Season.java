package org.example.agrotrack.dashboard.domain.model.valueobjects;

public record Season(String value) {

    public Season {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("dashboard.season.error.blank");
        }
        value = value.trim();
    }
}
