package org.example.agrotrack.identity.infrastructure.repositories;

import org.example.agrotrack.identity.infrastructure.entities.AlertPreferencePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlertPreferencePersistenceRepository extends JpaRepository<AlertPreferencePersistenceEntity, String> {
    Optional<AlertPreferencePersistenceEntity> findByUserId(String userId);
}
