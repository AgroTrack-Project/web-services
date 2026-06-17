package org.example.agrotrack.dashboard.infrastructure.persistence.jpa.repositories;

import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.entities.WaterConsumptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWaterConsumptionRepository extends JpaRepository<WaterConsumptionEntity, String> {
}
