package org.example.agrotrack.farming.infrastructure.repositories;

import org.example.agrotrack.farming.infrastructure.entities.CropPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropPersistenceRepository extends JpaRepository<CropPersistenceEntity, Long> {

    List<CropPersistenceEntity> findByPlotId(Long plotId);
}
