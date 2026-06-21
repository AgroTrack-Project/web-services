package org.example.agrotrack.farming.domain.model.aggregates;

import org.example.agrotrack.farming.domain.model.valueobjects.CropStatus;
import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.time.LocalDate;
import java.util.Objects;

public class Crop extends AbstractDomainAggregateRoot<Crop> {

    private final Long id;
    private final Long plotId;
    private String type;
    private LocalDate sowingDate;
    private LocalDate harvestDate;
    private CropStatus status;

    private Crop(
            Long id,
            Long plotId,
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

    public static Crop create(Long plotId, String type, LocalDate sowingDate, LocalDate harvestDate) {
        return new Crop(null, plotId, type, sowingDate, harvestDate, CropStatus.ACTIVE);
    }

    public static Crop restore(
            Long id,
            Long plotId,
            String type,
            LocalDate sowingDate,
            LocalDate harvestDate,
            CropStatus status
    ) {
        return new Crop(id, plotId, type, sowingDate, harvestDate, status);
    }

    public void update(String type, LocalDate sowingDate, LocalDate harvestDate) {
        this.type = Objects.requireNonNull(type, "type must not be null");
        this.sowingDate = Objects.requireNonNull(sowingDate, "sowingDate must not be null");
        this.harvestDate = harvestDate;
    }

    public void markAsHarvested(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
        this.status = CropStatus.HARVESTED;
    }

    public Long getId() {
        return id;
    }

    public Long getPlotId() {
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
