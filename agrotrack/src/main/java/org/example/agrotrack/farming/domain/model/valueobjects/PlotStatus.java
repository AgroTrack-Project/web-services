package org.example.agrotrack.farming.domain.model.valueobjects;

/**
 * Lifecycle state of a {@link org.example.agrotrack.farming.domain.model.aggregates.Plot}.
 * Every plot is created {@code ACTIVE}; {@code INACTIVE} is kept for plots persisted before
 * plot deletion became a hard delete and is not assigned by any current command handler.
 */
public enum PlotStatus {
    ACTIVE,
    INACTIVE
}
