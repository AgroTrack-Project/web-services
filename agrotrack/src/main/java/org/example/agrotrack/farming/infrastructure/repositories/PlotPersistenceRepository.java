package org.example.agrotrack.farming.infrastructure.repositories;

import org.example.agrotrack.farming.infrastructure.entities.PlotPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlotPersistenceRepository extends JpaRepository<PlotPersistenceEntity, Long> {

    List<PlotPersistenceEntity> findByUserId(String userId);
}
