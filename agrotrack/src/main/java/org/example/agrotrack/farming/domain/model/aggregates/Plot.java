package org.example.agrotrack.farming.domain.model.aggregates;

import org.example.agrotrack.farming.domain.model.valueobjects.PlotStatus;
import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Aggregate root for a farming plot owned by a single user. A plot is the unit of land
 * that {@link Crop} instances are sown on, and the anchor that harvest metrics (yield,
 * water usage, loss) are eventually attributed to via {@code HarvestMetricsGenerator}.
 */
public class Plot extends AbstractDomainAggregateRoot<Plot> {

    private final String id;
    private final String userId;
    private String name;
    private String location;
    private Double sizeHectares;
    // ACTIVE is the only status ever assigned by current write paths (create/update); INACTIVE
    // is retained for rows persisted before deletion became a hard delete and is not otherwise reachable.
    private PlotStatus status;
    private final LocalDateTime createdAt;

    private Plot(
            String id,
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

    /**
     * Creates a brand-new plot in {@link PlotStatus#ACTIVE}. The id is left {@code null} here;
     * the persistence adapter assigns it on first save.
     */
    public static Plot create(String userId, String name, String location, Double sizeHectares) {
        return new Plot(null, userId, name, location, sizeHectares, PlotStatus.ACTIVE, LocalDateTime.now());
    }

    /**
     * Rehydrates a plot from persisted state, preserving its existing id, status, and creation
     * timestamp. Used by the persistence assembler rather than application code.
     */
    public static Plot restore(
            String id,
            String userId,
            String name,
            String location,
            Double sizeHectares,
            PlotStatus status,
            LocalDateTime createdAt
    ) {
        return new Plot(id, userId, name, location, sizeHectares, status, createdAt);
    }

    /**
     * Updates the mutable descriptive fields of the plot. Ownership ({@code userId}) and status
     * are not editable through this method.
     */
    public void update(String name, String location, Double sizeHectares) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.location = Objects.requireNonNull(location, "location must not be null");
        this.sizeHectares = Objects.requireNonNull(sizeHectares, "sizeHectares must not be null");
    }

    public String getId() {
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
