package org.example.agrotrack.dashboard.infrastructure.persistence.jpa.repositories;

import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.entities.YieldSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaYieldSummaryRepository extends JpaRepository<YieldSummaryEntity, String> {
}
