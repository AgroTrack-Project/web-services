package org.example.agrotrack.soilmonitoring.infrastructure.assemblers;

import org.example.agrotrack.soilmonitoring.domain.model.IrrigationRecommendationStatus;
import org.example.agrotrack.soilmonitoring.domain.model.IrrigationUrgency;
import org.example.agrotrack.soilmonitoring.domain.model.aggregates.IrrigationRecommendation;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.PlotId;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.SoilRecordId;
import org.example.agrotrack.soilmonitoring.infrastructure.entities.IrrigationRecommendationPersistenceEntity;

public final class IrrigationRecommendationPersistenceAssembler {

    private IrrigationRecommendationPersistenceAssembler() {
    }

    public static IrrigationRecommendation toDomainFromPersistence(IrrigationRecommendationPersistenceEntity entity) {
        return IrrigationRecommendation.restore(
                entity.getId(),
                new PlotId(entity.getPlotId()),
                new SoilRecordId(entity.getSoilRecordId()),
                entity.getMessage(),
                IrrigationUrgency.valueOf(entity.getUrgency()),
                IrrigationRecommendationStatus.valueOf(entity.getStatus()),
                entity.getGeneratedAt(),
                entity.getRespondedAt()
        );
    }

    public static IrrigationRecommendationPersistenceEntity toPersistenceFromDomain(
            IrrigationRecommendation recommendation
    ) {
        IrrigationRecommendationPersistenceEntity entity = new IrrigationRecommendationPersistenceEntity();
        copyToPersistenceFromDomain(recommendation, entity);
        return entity;
    }

    public static void copyToPersistenceFromDomain(
            IrrigationRecommendation recommendation,
            IrrigationRecommendationPersistenceEntity entity
    ) {
        entity.setPlotId(recommendation.getPlotId().value());
        entity.setSoilRecordId(recommendation.getSoilRecordId().value());
        entity.setMessage(recommendation.getMessage());
        entity.setUrgency(recommendation.getUrgency().name());
        entity.setStatus(recommendation.getStatus().name());
        entity.setGeneratedAt(recommendation.getGeneratedAt());
        entity.setRespondedAt(recommendation.getRespondedAt());
    }
}