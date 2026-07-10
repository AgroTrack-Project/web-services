package org.example.agrotrack.farming.infrastructure.repositories;

import org.example.agrotrack.farming.infrastructure.entities.PlotPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data repository over the raw {@link PlotPersistenceEntity}. Only used internally by
 * {@code PlotRepositoryImpl}; domain and application code depend on the {@code PlotRepository}
 * port instead.
 */
public interface PlotPersistenceRepository extends JpaRepository<PlotPersistenceEntity, String> {

    List<PlotPersistenceEntity> findByUserId(String userId);
}
