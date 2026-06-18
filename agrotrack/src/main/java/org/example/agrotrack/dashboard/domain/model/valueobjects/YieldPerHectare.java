package org.example.agrotrack.dashboard.domain.model.valueobjects;

public record YieldPerHectare(double value) {

    public YieldPerHectare {
        if (value <= 0) {
            throw new IllegalArgumentException("dashboard.yield.error.non-positive");
        }
    }
}
