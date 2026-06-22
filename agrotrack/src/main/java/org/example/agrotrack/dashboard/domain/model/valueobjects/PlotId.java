package org.example.agrotrack.dashboard.domain.model.valueobjects;

public record PlotId(String value) {

    public PlotId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("dashboard.plot.error.id-blank");
        }
        value = value.trim();
    }
}
