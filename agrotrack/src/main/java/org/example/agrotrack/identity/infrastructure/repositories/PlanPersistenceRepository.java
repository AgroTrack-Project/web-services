package org.example.agrotrack.identity.infrastructure.repositories;

import org.example.agrotrack.identity.domain.model.valueobjects.PlanType;
import org.example.agrotrack.identity.infrastructure.entities.PlanPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanPersistenceRepository extends JpaRepository<PlanPersistenceEntity, Long> {
    Optional<PlanPersistenceEntity> findByPlanType(PlanType planType);
}
