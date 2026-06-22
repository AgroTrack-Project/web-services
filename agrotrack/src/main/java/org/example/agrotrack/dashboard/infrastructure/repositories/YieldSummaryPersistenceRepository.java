package org.example.agrotrack.dashboard.infrastructure.repositories;

import org.example.agrotrack.dashboard.infrastructure.entities.YieldSummaryPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YieldSummaryPersistenceRepository extends JpaRepository<YieldSummaryPersistenceEntity, String> {
}
