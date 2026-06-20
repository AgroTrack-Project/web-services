package org.example.agrotrack.soilmonitoring.interfaces.rest.transform;

import org.example.agrotrack.soilmonitoring.domain.model.commands.CreateIrrigationRecommendationCommand;
import org.example.agrotrack.soilmonitoring.interfaces.rest.resource.CreateIrrigationRecommendationResource;

public final class CreateIrrigationRecommendationCommandFromResourceAssembler {

    private CreateIrrigationRecommendationCommandFromResourceAssembler() {
    }

    public static CreateIrrigationRecommendationCommand toCommandFromResource(
            CreateIrrigationRecommendationResource resource
    ) {
        return new CreateIrrigationRecommendationCommand(
                resource.plotId(),
                resource.soilRecordId(),
                resource.message(),
                resource.urgency(),
                resource.status(),
                resource.generatedAt(),
                resource.respondedAt()
        );
    }
}