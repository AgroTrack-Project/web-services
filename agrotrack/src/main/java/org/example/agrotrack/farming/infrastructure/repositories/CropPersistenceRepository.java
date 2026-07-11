package org.example.agrotrack.farming.infrastructure.repositories;

import org.example.agrotrack.farming.infrastructure.entities.CropPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data repository over the raw {@link CropPersistenceEntity}. Only used internally by
 * {@code CropRepositoryImpl}; domain and application code depend on the {@code CropRepository}
 * port instead.
 */
public interface CropPersistenceRepository extends JpaRepository<CropPersistenceEntity, String> {

    List<CropPersistenceEntity> findByPlotId(String plotId);
}
