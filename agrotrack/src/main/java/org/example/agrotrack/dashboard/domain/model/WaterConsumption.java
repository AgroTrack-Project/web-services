package org.example.agrotrack.dashboard.domain.model;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.time.Instant;
import java.util.Objects;

public class WaterConsumption extends AbstractDomainAggregateRoot<WaterConsumption> {

    private final String id;
    private final String plotId;
    private final double totalLiters;
    private final String season;
    private final Instant calculatedAt;

    private WaterConsumption(
            String id,
            String plotId,
            double totalLiters,
            String season,
            Instant calculatedAt
    ) {
        this.id = id;
        this.plotId = Objects.requireNonNull(plotId, "plotId must not be null");
        this.totalLiters = totalLiters;
        this.season = Objects.requireNonNull(season, "season must not be null");
        this.calculatedAt = calculatedAt;
    }

    public static WaterConsumption restore(
            String id,
            String plotId,
            double totalLiters,
            String season,
            Instant calculatedAt
    ) {
        return new WaterConsumption(id, plotId, totalLiters, season, calculatedAt);
    }

    public String getId() {
        return id;
    }

    public String getPlotId() {
        return plotId;
    }

    public double getTotalLiters() {
        return totalLiters;
    }

    public String getSeason() {
        return season;
    }

    public Instant getCalculatedAt() {
        return calculatedAt;
    }
}
