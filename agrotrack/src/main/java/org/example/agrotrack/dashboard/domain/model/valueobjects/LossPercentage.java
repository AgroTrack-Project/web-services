package org.example.agrotrack.dashboard.domain.model.valueobjects;

public record LossPercentage(double value) {

    public LossPercentage {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("dashboard.loss.error.percentage-out-of-range");
        }
    }
}
