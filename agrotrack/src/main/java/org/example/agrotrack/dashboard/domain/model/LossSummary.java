package org.example.agrotrack.dashboard.domain.model;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.time.Instant;
import java.util.Objects;

public class LossSummary extends AbstractDomainAggregateRoot<LossSummary> {

    private final String id;
    private final String plotId;
    private final double lossPercentage;
    private final String cause;
    private final String season;
    private final Instant calculatedAt;

    private LossSummary(
            String id,
            String plotId,
            double lossPercentage,
            String cause,
            String season,
            Instant calculatedAt
    ) {
        this.id = id;
        this.plotId = Objects.requireNonNull(plotId, "plotId must not be null");
        this.lossPercentage = lossPercentage;
        this.cause = Objects.requireNonNull(cause, "cause must not be null");
        this.season = Objects.requireNonNull(season, "season must not be null");
        this.calculatedAt = calculatedAt;
    }

    public static LossSummary restore(
            String id,
            String plotId,
            double lossPercentage,
            String cause,
            String season,
            Instant calculatedAt
    ) {
        return new LossSummary(id, plotId, lossPercentage, cause, season, calculatedAt);
    }

    public String getId() {
        return id;
    }

    public String getPlotId() {
        return plotId;
    }

    public double getLossPercentage() {
        return lossPercentage;
    }

    public String getCause() {
        return cause;
    }

    public String getSeason() {
        return season;
    }

    public Instant getCalculatedAt() {
        return calculatedAt;
    }
}
