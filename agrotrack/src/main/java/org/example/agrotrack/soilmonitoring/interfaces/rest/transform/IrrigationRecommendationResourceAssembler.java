package org.example.agrotrack.soilmonitoring.interfaces.rest.transform;

import org.example.agrotrack.soilmonitoring.domain.model.aggregates.IrrigationRecommendation;
import org.example.agrotrack.soilmonitoring.interfaces.rest.resource.IrrigationRecommendationResource;

public final class IrrigationRecommendationResourceAssembler {

    private IrrigationRecommendationResourceAssembler() {
    }

    public static IrrigationRecommendationResource toResource(IrrigationRecommendation recommendation) {
        return new IrrigationRecommendationResource(
                recommendation.getId(),
                recommendation.getPlotId().value(),
                recommendation.getSoilRecordId().value(),
                recommendation.getMessage(),
                recommendation.getUrgency().name(),
                recommendation.getStatus().name(),
                recommendation.getGeneratedAt(),
                recommendation.getRespondedAt()
        );
    }
}