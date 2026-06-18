package org.example.agrotrack.dashboard.domain.model.aggregates;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;
import org.example.agrotrack.dashboard.domain.model.valueobjects.LossCause;
import org.example.agrotrack.dashboard.domain.model.valueobjects.LossPercentage;
import org.example.agrotrack.dashboard.domain.model.valueobjects.PlotId;
import org.example.agrotrack.dashboard.domain.model.valueobjects.Season;

import java.time.Instant;
import java.util.Objects;

public class LossSummary extends AbstractDomainAggregateRoot<LossSummary> {

    private final String id;
    private final PlotId plotId;
    private final LossPercentage lossPercentage;
    private final LossCause cause;
    private final Season season;
    private final Instant calculatedAt;

    private LossSummary(
            String id,
            PlotId plotId,
            LossPercentage lossPercentage,
            LossCause cause,
            Season season,
            Instant calculatedAt
    ) {
        this.id = id;
        this.plotId = Objects.requireNonNull(plotId, "plotId must not be null");
        this.lossPercentage = Objects.requireNonNull(lossPercentage, "lossPercentage must not be null");
        this.cause = Objects.requireNonNull(cause, "cause must not be null");
        this.season = Objects.requireNonNull(season, "season must not be null");
        this.calculatedAt = calculatedAt;
    }

    public static LossSummary restore(
            String id,
            PlotId plotId,
            LossPercentage lossPercentage,
            LossCause cause,
            Season season,
            Instant calculatedAt
    ) {
        return new LossSummary(id, plotId, lossPercentage, cause, season, calculatedAt);
    }

    public String getId() {
        return id;
    }

    public PlotId getPlotId() {
        return plotId;
    }

    public LossPercentage getLossPercentage() {
        return lossPercentage;
    }

    public LossCause getCause() {
        return cause;
    }

    public Season getSeason() {
        return season;
    }

    public Instant getCalculatedAt() {
        return calculatedAt;
    }
}
