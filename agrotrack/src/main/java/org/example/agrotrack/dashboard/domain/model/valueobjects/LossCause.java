package org.example.agrotrack.dashboard.domain.model.valueobjects;

public record LossCause(String value) {

    private static final int MAX_LENGTH = 200;

    public LossCause {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("dashboard.loss.error.cause-blank");
        }
        value = value.trim();
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("dashboard.loss.error.cause-too-long");
        }
    }
}
