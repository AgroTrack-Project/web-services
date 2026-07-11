package org.example.agrotrack.dashboard.interfaces.rest.transform;

import org.example.agrotrack.dashboard.domain.model.commands.CreateLossSummaryCommand;
import org.example.agrotrack.dashboard.interfaces.rest.resource.CreateLossSummaryResource;

public final class CreateLossSummaryCommandFromResourceAssembler {

    private CreateLossSummaryCommandFromResourceAssembler() {
    }

    public static CreateLossSummaryCommand toCommandFromResource(CreateLossSummaryResource resource) {
        return new CreateLossSummaryCommand(
                resource.plotId(),
                resource.lossPercentage(),
                resource.cause(),
                resource.season()
        );
    }
}
