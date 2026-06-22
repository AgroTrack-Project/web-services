package org.example.agrotrack.soilmonitoring.domain.model.valueobjects;

public record PlotId(String value) {

    public PlotId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("plot_id is required");
        }

        value = value.trim();
    }
}