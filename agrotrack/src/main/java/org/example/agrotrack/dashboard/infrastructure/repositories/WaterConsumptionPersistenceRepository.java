package org.example.agrotrack.dashboard.infrastructure.repositories;

import org.example.agrotrack.dashboard.infrastructure.entities.WaterConsumptionPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterConsumptionPersistenceRepository extends JpaRepository<WaterConsumptionPersistenceEntity, String> {
}
