package org.example.agrotrack.identity.interfaces.rest.transform;

import org.example.agrotrack.identity.domain.model.aggregates.Plan;
import org.example.agrotrack.identity.interfaces.rest.resource.PlanResource;

public final class PlanResourceAssembler {

    private PlanResourceAssembler() {}

    public static PlanResource toResource(Plan plan) {
        return new PlanResource(
                plan.getId(), // Long
                plan.getPlanType().name(),
                plan.getPrice(),
                plan.getMaxPlots(),
                plan.isDashboardEnabled(),
                plan.isExportEnabled(),
                plan.hasPrioritySupport()
        );
    }
}
