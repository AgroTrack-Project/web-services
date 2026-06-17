package org.example.agrotrack.dashboard.infrastructure.persistence.jpa.repositories;

import org.example.agrotrack.dashboard.infrastructure.persistence.jpa.entities.LossSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLossSummaryRepository extends JpaRepository<LossSummaryEntity, String> {
}
