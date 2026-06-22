package org.example.agrotrack.identity.infrastructure.persistence;

import org.example.agrotrack.identity.domain.model.aggregates.Plan;
import org.example.agrotrack.identity.domain.model.valueobjects.PlanType;
import org.example.agrotrack.identity.domain.repositories.PlanRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class IdentityDataSeeder implements ApplicationRunner {

    private final PlanRepository planRepository;

    public IdentityDataSeeder(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (planRepository.count() > 0) return;

        planRepository.save(Plan.create(PlanType.BASIC, 39.0, 3, false, false, false));
        planRepository.save(Plan.create(PlanType.PRO, 85.0, 15, true, true, false));
        planRepository.save(Plan.create(PlanType.ENTERPRISE, 149.0, 100, true, true, true));
    }
}
