package org.example.agrotrack.soilmonitoring.infrastructure.repositories;

import org.example.agrotrack.soilmonitoring.infrastructure.entities.IrrigationRecommendationPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IrrigationRecommendationPersistenceRepository extends JpaRepository<IrrigationRecommendationPersistenceEntity, String> {

    List<IrrigationRecommendationPersistenceEntity> findByPlotIdOrderByGeneratedAtDesc(String plotId);

    void deleteByPlotId(String plotId);
}