package org.example.agrotrack.soilmonitoring.infrastructure.repositories;

import org.example.agrotrack.soilmonitoring.infrastructure.entities.SoilRecordPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoilRecordPersistenceRepository extends JpaRepository<SoilRecordPersistenceEntity, String> {

    List<SoilRecordPersistenceEntity> findByPlotIdOrderByRecordedAtDesc(String plotId);

    void deleteByPlotId(String plotId);
}