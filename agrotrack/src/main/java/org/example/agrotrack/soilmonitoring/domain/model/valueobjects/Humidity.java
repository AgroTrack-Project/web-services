package org.example.agrotrack.soilmonitoring.domain.model.valueobjects;

public record Humidity(Double value) {

    public Humidity {
        if (value == null) {
            throw new IllegalArgumentException("humidity is required");
        }

        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("humidity must be between 0 and 100");
        }
    }
}