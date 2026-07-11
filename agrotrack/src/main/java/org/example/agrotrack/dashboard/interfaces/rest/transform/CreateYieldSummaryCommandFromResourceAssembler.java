package org.example.agrotrack.dashboard.interfaces.rest.transform;

import org.example.agrotrack.dashboard.domain.model.commands.CreateYieldSummaryCommand;
import org.example.agrotrack.dashboard.interfaces.rest.resource.CreateYieldSummaryResource;

public final class CreateYieldSummaryCommandFromResourceAssembler {

    private CreateYieldSummaryCommandFromResourceAssembler() {
    }

    public static CreateYieldSummaryCommand toCommandFromResource(CreateYieldSummaryResource resource) {
        return new CreateYieldSummaryCommand(resource.plotId(), resource.yieldPerHectare(), resource.season());
    }
}
