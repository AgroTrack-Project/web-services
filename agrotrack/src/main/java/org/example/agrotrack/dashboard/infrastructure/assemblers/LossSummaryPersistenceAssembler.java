package org.example.agrotrack.dashboard.infrastructure.assemblers;

import org.example.agrotrack.dashboard.domain.model.aggregates.LossSummary;
import org.example.agrotrack.dashboard.domain.model.valueobjects.LossCause;
import org.example.agrotrack.dashboard.domain.model.valueobjects.LossPercentage;
import org.example.agrotrack.dashboard.domain.model.valueobjects.PlotId;
import org.example.agrotrack.dashboard.domain.model.valueobjects.Season;
import org.example.agrotrack.dashboard.infrastructure.entities.LossSummaryPersistenceEntity;

public final class LossSummaryPersistenceAssembler {

    private LossSummaryPersistenceAssembler() {
    }

    public static LossSummary toDomainFromPersistence(LossSummaryPersistenceEntity entity) {
        return LossSummary.restore(
                entity.getId(),
                new PlotId(entity.getPlotId()),
                new LossPercentage(entity.getLossPercentage()),
                new LossCause(entity.getCause()),
                new Season(entity.getSeason()),
                entity.getCalculatedAt()
        );
    }

    public static LossSummaryPersistenceEntity toPersistenceFromDomain(LossSummary summary) {
        LossSummaryPersistenceEntity entity = new LossSummaryPersistenceEntity();
        entity.setPlotId(summary.getPlotId().value());
        entity.setLossPercentage(summary.getLossPercentage().value());
        entity.setCause(summary.getCause().value());
        entity.setSeason(summary.getSeason().value());
        entity.setCalculatedAt(summary.getCalculatedAt());
        return entity;
    }
}
