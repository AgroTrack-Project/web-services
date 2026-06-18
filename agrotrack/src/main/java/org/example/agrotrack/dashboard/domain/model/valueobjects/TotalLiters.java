package org.example.agrotrack.dashboard.domain.model.valueobjects;

public record TotalLiters(double value) {

    public TotalLiters {
        if (value < 0) {
            throw new IllegalArgumentException("dashboard.water.error.negative-liters");
        }
    }
}
