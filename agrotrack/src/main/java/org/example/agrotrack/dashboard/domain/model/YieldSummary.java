package org.example.agrotrack.dashboard.domain.model;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.time.Instant;
import java.util.Objects;

public class YieldSummary extends AbstractDomainAggregateRoot<YieldSummary> {

    private final String id;
    private final String plotId;
    private final double yieldPerHectare;
    private final String season;
    private final Instant calculatedAt;

    private YieldSummary(
            String id,
            String plotId,
            double yieldPerHectare,
            String season,
            Instant calculatedAt
    ) {
        this.id = id;
        this.plotId = Objects.requireNonNull(plotId, "plotId must not be null");
        this.yieldPerHectare = yieldPerHectare;
        this.season = Objects.requireNonNull(season, "season must not be null");
        this.calculatedAt = calculatedAt;
    }

    public static YieldSummary restore(
            String id,
            String plotId,
            double yieldPerHectare,
            String season,
            Instant calculatedAt
    ) {
        return new YieldSummary(id, plotId, yieldPerHectare, season, calculatedAt);
    }

    public String getId() {
        return id;
    }

    public String getPlotId() {
        return plotId;
    }

    public double getYieldPerHectare() {
        return yieldPerHectare;
    }

    public String getSeason() {
        return season;
    }

    public Instant getCalculatedAt() {
        return calculatedAt;
    }
}
