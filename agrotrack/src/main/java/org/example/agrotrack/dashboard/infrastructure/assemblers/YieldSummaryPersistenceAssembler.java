package org.example.agrotrack.dashboard.infrastructure.assemblers;

import org.example.agrotrack.dashboard.domain.model.aggregates.YieldSummary;
import org.example.agrotrack.dashboard.domain.model.valueobjects.PlotId;
import org.example.agrotrack.dashboard.domain.model.valueobjects.Season;
import org.example.agrotrack.dashboard.domain.model.valueobjects.YieldPerHectare;
import org.example.agrotrack.dashboard.infrastructure.entities.YieldSummaryPersistenceEntity;

public final class YieldSummaryPersistenceAssembler {

    private YieldSummaryPersistenceAssembler() {
    }

    public static YieldSummary toDomainFromPersistence(YieldSummaryPersistenceEntity entity) {
        return YieldSummary.restore(
                entity.getId(),
                new PlotId(entity.getPlotId()),
                new YieldPerHectare(entity.getYieldPerHectare()),
                new Season(entity.getSeason()),
                entity.getCalculatedAt()
        );
    }

    public static YieldSummaryPersistenceEntity toPersistenceFromDomain(YieldSummary summary) {
        YieldSummaryPersistenceEntity entity = new YieldSummaryPersistenceEntity();
        entity.setPlotId(summary.getPlotId().value());
        entity.setYieldPerHectare(summary.getYieldPerHectare().value());
        entity.setSeason(summary.getSeason().value());
        entity.setCalculatedAt(summary.getCalculatedAt());
        return entity;
    }
}
