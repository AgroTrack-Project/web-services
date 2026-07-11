package org.example.agrotrack.identity.infrastructure.assemblers;

import org.example.agrotrack.identity.domain.model.aggregates.Plan;
import org.example.agrotrack.identity.infrastructure.entities.PlanPersistenceEntity;

public final class PlanPersistenceAssembler {

    private PlanPersistenceAssembler() {}

    public static Plan toDomainFromPersistence(PlanPersistenceEntity entity) {
        return Plan.restore(
                entity.getId(),
                entity.getPlanType(),
                entity.getPrice(),
                entity.getMaxPlots(),
                entity.isDashboardEnabled(),
                entity.isExportEnabled(),
                entity.isHasPrioritySupport()
        );
    }

    public static PlanPersistenceEntity toPersistenceFromDomain(Plan plan) {
        PlanPersistenceEntity entity = new PlanPersistenceEntity();
        entity.setPlanType(plan.getPlanType());
        entity.setPrice(plan.getPrice());
        entity.setMaxPlots(plan.getMaxPlots());
        entity.setDashboardEnabled(plan.isDashboardEnabled());
        entity.setExportEnabled(plan.isExportEnabled());
        entity.setHasPrioritySupport(plan.hasPrioritySupport());
        return entity;
    }
}
