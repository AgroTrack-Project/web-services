package org.example.agrotrack.dashboard.infrastructure.repositories;

import org.example.agrotrack.dashboard.infrastructure.entities.LossSummaryPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LossSummaryPersistenceRepository extends JpaRepository<LossSummaryPersistenceEntity, String> {
}
