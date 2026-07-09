package org.example.agrotrack.iam.domain.repositories;

import org.example.agrotrack.iam.domain.model.entities.Role;
import org.example.agrotrack.iam.domain.model.valueobjects.Roles;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(Roles name);

    List<Role> findAll();

    Role save(Role role);

    boolean existsByName(Roles name);
}
