package org.example.agrotrack.dashboard.domain.model.aggregates;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;
import org.example.agrotrack.dashboard.domain.model.valueobjects.PlotId;
import org.example.agrotrack.dashboard.domain.model.valueobjects.Season;
import org.example.agrotrack.dashboard.domain.model.valueobjects.TotalLiters;

import java.time.Instant;
import java.util.Objects;

public class WaterConsumption extends AbstractDomainAggregateRoot<WaterConsumption> {

    private final String id;
    private final PlotId plotId;
    private final TotalLiters totalLiters;
    private final Season season;
    private final Instant calculatedAt;

    private WaterConsumption(
            String id,
            PlotId plotId,
            TotalLiters totalLiters,
            Season season,
            Instant calculatedAt
    ) {
        this.id = id;
        this.plotId = Objects.requireNonNull(plotId, "plotId must not be null");
        this.totalLiters = Objects.requireNonNull(totalLiters, "totalLiters must not be null");
        this.season = Objects.requireNonNull(season, "season must not be null");
        this.calculatedAt = calculatedAt;
    }

    public static WaterConsumption restore(
            String id,
            PlotId plotId,
            TotalLiters totalLiters,
            Season season,
            Instant calculatedAt
    ) {
        return new WaterConsumption(id, plotId, totalLiters, season, calculatedAt);
    }

    public String getId() {
        return id;
    }

    public PlotId getPlotId() {
        return plotId;
    }

    public TotalLiters getTotalLiters() {
        return totalLiters;
    }

    public Season getSeason() {
        return season;
    }

    public Instant getCalculatedAt() {
        return calculatedAt;
    }
}
