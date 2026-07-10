package org.example.agrotrack.farming.domain.model.valueobjects;

/**
 * Lifecycle state of a {@link org.example.agrotrack.farming.domain.model.aggregates.Crop}.
 * A crop starts {@code ACTIVE} on creation and moves to {@code HARVESTED} exactly once, via
 * {@code Crop.harvest()}. {@code LOST} is defined for future crop-loss tracking but is not
 * currently assigned by any command handler.
 */
public enum CropStatus {
    ACTIVE,
    HARVESTED,
    LOST
}
