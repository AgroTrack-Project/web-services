package org.example.agrotrack.soilmonitoring.domain.model.valueobjects;

public record Temperature(Double value) {

    public Temperature {
        if (value == null) {
            throw new IllegalArgumentException("temperature is required");
        }
    }
}