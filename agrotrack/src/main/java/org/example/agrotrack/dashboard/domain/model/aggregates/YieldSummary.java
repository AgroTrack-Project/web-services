package org.example.agrotrack.dashboard.domain.model.aggregates;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;
import org.example.agrotrack.dashboard.domain.model.valueobjects.PlotId;
import org.example.agrotrack.dashboard.domain.model.valueobjects.Season;
import org.example.agrotrack.dashboard.domain.model.valueobjects.YieldPerHectare;

import java.time.Instant;
import java.util.Objects;

public class YieldSummary extends AbstractDomainAggregateRoot<YieldSummary> {

    private final String id;
    private final PlotId plotId;
    private final YieldPerHectare yieldPerHectare;
    private final Season season;
    private final Instant calculatedAt;

    private YieldSummary(
            String id,
            PlotId plotId,
            YieldPerHectare yieldPerHectare,
            Season season,
            Instant calculatedAt
    ) {
        this.id = id;
        this.plotId = Objects.requireNonNull(plotId, "plotId must not be null");
        this.yieldPerHectare = Objects.requireNonNull(yieldPerHectare, "yieldPerHectare must not be null");
        this.season = Objects.requireNonNull(season, "season must not be null");
        this.calculatedAt = calculatedAt;
    }

    public static YieldSummary create(
            PlotId plotId,
            YieldPerHectare yieldPerHectare,
            Season season
    ) {
        return new YieldSummary(null, plotId, yieldPerHectare, season, Instant.now());
    }

    public static YieldSummary restore(
            String id,
            PlotId plotId,
            YieldPerHectare yieldPerHectare,
            Season season,
            Instant calculatedAt
    ) {
        return new YieldSummary(id, plotId, yieldPerHectare, season, calculatedAt);
    }

    public String getId() {
        return id;
    }

    public PlotId getPlotId() {
        return plotId;
    }

    public YieldPerHectare getYieldPerHectare() {
        return yieldPerHectare;
    }

    public Season getSeason() {
        return season;
    }

    public Instant getCalculatedAt() {
        return calculatedAt;
    }
}
