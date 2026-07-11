package org.example.agrotrack.soilmonitoring.interfaces.rest.transform;

import org.example.agrotrack.soilmonitoring.domain.model.commands.UpdateIrrigationRecommendationCommand;
import org.example.agrotrack.soilmonitoring.interfaces.rest.resource.UpdateIrrigationRecommendationResource;

public final class UpdateIrrigationRecommendationCommandFromResourceAssembler {

    private UpdateIrrigationRecommendationCommandFromResourceAssembler() {
    }

    public static UpdateIrrigationRecommendationCommand toCommandFromResource(
            String id,
            UpdateIrrigationRecommendationResource resource
    ) {
        return new UpdateIrrigationRecommendationCommand(
                id,
                resource.status(),
                resource.respondedAt()
        );
    }
}