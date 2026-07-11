package org.example.agrotrack.iam.infrastructure.persistence.jpa.repositories;

import org.example.agrotrack.iam.domain.model.valueobjects.Roles;
import org.example.agrotrack.iam.infrastructure.persistence.jpa.entities.RolePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolePersistenceRepository extends JpaRepository<RolePersistenceEntity, String> {

    Optional<RolePersistenceEntity> findByName(Roles name);

    boolean existsByName(Roles name);
}
