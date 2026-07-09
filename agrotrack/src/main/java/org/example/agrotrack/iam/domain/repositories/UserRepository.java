package org.example.agrotrack.iam.domain.repositories;

import org.example.agrotrack.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    User save(User user);

    boolean existsByEmail(String email);
}
