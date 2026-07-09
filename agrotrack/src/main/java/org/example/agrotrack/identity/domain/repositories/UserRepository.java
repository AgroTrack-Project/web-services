package org.example.agrotrack.identity.domain.repositories;

import org.example.agrotrack.identity.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    Optional<User> findByIamUserId(String iamUserId);
    List<User> findAll();
    User save(User user);
    boolean existsByEmail(String email);
    long count();
}
