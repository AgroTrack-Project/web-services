package org.example.agrotrack.farming.domain.model.aggregates;

import org.example.agrotrack.farming.domain.model.valueobjects.PlotStatus;
import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.time.LocalDateTime;
import java.util.Objects;

public class Plot extends AbstractDomainAggregateRoot<Plot> {

    private final Long id;
    private final String userId;
    private String name;
    private String location;
    private Double sizeHectares;
    private PlotStatus status;
    private final LocalDateTime createdAt;

    private Plot(
            Long id,
            String userId,
            String name,
            String location,
            Double sizeHectares,
            PlotStatus status,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.location = Objects.requireNonNull(location, "location must not be null");
        this.sizeHectares = Objects.requireNonNull(sizeHectares, "sizeHectares must not be null");
        this.status = Objects.requireNonNull(status, "status must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static Plot create(String userId, String name, String location, Double sizeHectares) {
        return new Plot(null, userId, name, location, sizeHectares, PlotStatus.ACTIVE, LocalDateTime.now());
    }

    public static Plot restore(
            Long id,
            String userId,
            String name,
            String location,
            Double sizeHectares,
            PlotStatus status,
            LocalDateTime createdAt
    ) {
        return new Plot(id, userId, name, location, sizeHectares, status, createdAt);
    }

    public void update(String name, String location, Double sizeHectares) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.location = Objects.requireNonNull(location, "location must not be null");
        this.sizeHectares = Objects.requireNonNull(sizeHectares, "sizeHectares must not be null");
    }

    public void deactivate() {
        this.status = PlotStatus.INACTIVE;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Double getSizeHectares() {
        return sizeHectares;
    }

    public PlotStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
