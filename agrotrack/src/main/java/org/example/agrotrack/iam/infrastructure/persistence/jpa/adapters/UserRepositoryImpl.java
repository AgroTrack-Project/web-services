package org.example.agrotrack.iam.infrastructure.persistence.jpa.adapters;

import org.example.agrotrack.iam.domain.model.aggregates.User;
import org.example.agrotrack.iam.domain.repositories.UserRepository;
import org.example.agrotrack.iam.infrastructure.persistence.jpa.assemblers.UserPersistenceAssembler;
import org.example.agrotrack.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;
import org.example.agrotrack.iam.infrastructure.persistence.jpa.repositories.UserPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("iamUserRepositoryImpl")
public class UserRepositoryImpl implements UserRepository {

    private final UserPersistenceRepository persistenceRepository;

    public UserRepositoryImpl(UserPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<User> findById(String id) {
        return persistenceRepository.findById(id).map(UserPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return persistenceRepository.findByEmail(email).map(UserPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<User> findAll() {
        return persistenceRepository.findAll().stream()
                .map(UserPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            UserPersistenceEntity entity = UserPersistenceAssembler.toPersistenceFromDomain(user);
            return UserPersistenceAssembler.toDomainFromPersistence(persistenceRepository.save(entity));
        }
        UserPersistenceEntity entity = persistenceRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalStateException("IAM user not found: " + user.getId()));
        UserPersistenceAssembler.updatePersistenceFromDomain(entity, user);
        return UserPersistenceAssembler.toDomainFromPersistence(persistenceRepository.save(entity));
    }

    @Override
    public boolean existsByEmail(String email) {
        return persistenceRepository.existsByEmail(email);
    }
}
