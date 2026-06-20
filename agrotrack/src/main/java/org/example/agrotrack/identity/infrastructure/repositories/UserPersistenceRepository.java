package org.example.agrotrack.identity.infrastructure.repositories;

import org.example.agrotrack.identity.infrastructure.entities.UserPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPersistenceRepository extends JpaRepository<UserPersistenceEntity, String> {
    Optional<UserPersistenceEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
