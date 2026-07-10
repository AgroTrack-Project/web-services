package org.example.agrotrack.iam.infrastructure.persistence.jpa.repositories;

import org.example.agrotrack.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("iamUserPersistenceRepository")
public interface UserPersistenceRepository extends JpaRepository<UserPersistenceEntity, String> {

    Optional<UserPersistenceEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
