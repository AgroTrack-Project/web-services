package org.example.agrotrack.farming.domain.model.aggregates;

import org.example.agrotrack.farming.domain.model.valueobjects.CropStatus;
import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Aggregate root for a single sowing cycle on a {@link Plot}. A crop tracks its own
 * lifecycle independently of the plot it belongs to, from sowing through to harvest.
 */
public class Crop extends AbstractDomainAggregateRoot<Crop> {

    private final String id;
    private final String plotId;
    private String type;
    private LocalDate sowingDate;
    // harvestDate is nullable until the crop is actually harvested (see harvest()); it is not
    // a planned/expected date, only the recorded actual harvest date.
    private LocalDate harvestDate;
    // CropStatus.LOST has no current write path (no method transitions a crop into it); it is
    // reserved for future crop-loss tracking or for describing previously-persisted rows.
    private CropStatus status;

    private Crop(
            String id,
            String plotId,
            String type,
            LocalDate sowingDate,
            LocalDate harvestDate,
            CropStatus status
    ) {
        this.id = id;
        this.plotId = Objects.requireNonNull(plotId, "plotId must not be null");
        this.type = Objects.requireNonNull(type, "type must not be null");
        this.sowingDate = Objects.requireNonNull(sowingDate, "sowingDate must not be null");
        this.harvestDate = harvestDate;
        this.status = Objects.requireNonNull(status, "status must not be null");
    }

    /**
     * Creates a brand-new crop in {@link CropStatus#ACTIVE}. {@code harvestDate} may be supplied
     * up front as a planned date even though the crop has not been harvested yet; the id is left
     * {@code null} and assigned on first save.
     */
    public static Crop create(String plotId, String type, LocalDate sowingDate, LocalDate harvestDate) {
        return new Crop(null, plotId, type, sowingDate, harvestDate, CropStatus.ACTIVE);
    }

    /**
     * Rehydrates a crop from persisted state, preserving its existing id and status. Used by the
     * persistence assembler rather than application code.
     */
    public static Crop restore(
            String id,
            String plotId,
            String type,
            LocalDate sowingDate,
            LocalDate harvestDate,
            CropStatus status
    ) {
        return new Crop(id, plotId, type, sowingDate, harvestDate, status);
    }

    /**
     * Updates the descriptive fields of the crop. This does not change {@code status}; it is
     * used for editing type/dates on a crop that has not necessarily been harvested yet.
     */
    public void update(String type, LocalDate sowingDate, LocalDate harvestDate) {
        this.type = Objects.requireNonNull(type, "type must not be null");
        this.sowingDate = Objects.requireNonNull(sowingDate, "sowingDate must not be null");
        this.harvestDate = harvestDate;
    }

    /**
     * Records the actual harvest and transitions the crop to {@link CropStatus#HARVESTED}.
     * This is a one-way transition: harvesting an already-harvested crop is rejected rather
     * than silently overwriting the recorded harvest date.
     */
    public void harvest(LocalDate harvestDate) {
        if (this.status == CropStatus.HARVESTED) {
            throw new IllegalStateException("crop.error.already-harvested");
        }
        this.harvestDate = Objects.requireNonNull(harvestDate, "harvestDate must not be null");
        this.status = CropStatus.HARVESTED;
    }

    public String getId() {
        return id;
    }

    public String getPlotId() {
        return plotId;
    }

    public String getType() {
        return type;
    }

    public LocalDate getSowingDate() {
        return sowingDate;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public CropStatus getStatus() {
        return status;
    }
}
