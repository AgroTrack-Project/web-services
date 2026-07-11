package org.example.agrotrack.identity.domain.repositories;

import org.example.agrotrack.identity.domain.model.aggregates.Plan;
import org.example.agrotrack.identity.domain.model.valueobjects.PlanType;

import java.util.List;
import java.util.Optional;

public interface PlanRepository {
    Optional<Plan> findById(Long id);
    Optional<Plan> findByPlanType(PlanType planType);
    List<Plan> findAll();
    Plan save(Plan plan);
    long count();
}
