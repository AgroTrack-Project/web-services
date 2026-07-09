package org.example.agrotrack.iam.infrastructure.persistence.jpa.adapters;

import org.example.agrotrack.iam.domain.model.entities.Role;
import org.example.agrotrack.iam.domain.model.valueobjects.Roles;
import org.example.agrotrack.iam.domain.repositories.RoleRepository;
import org.example.agrotrack.iam.infrastructure.persistence.jpa.assemblers.RolePersistenceAssembler;
import org.example.agrotrack.iam.infrastructure.persistence.jpa.repositories.RolePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final RolePersistenceRepository persistenceRepository;

    public RoleRepositoryImpl(RolePersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<Role> findByName(Roles name) {
        return persistenceRepository.findByName(name).map(RolePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Role> findAll() {
        return persistenceRepository.findAll().stream()
                .map(RolePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Role save(Role role) {
        var saved = persistenceRepository.save(RolePersistenceAssembler.toPersistenceFromDomain(role));
        return RolePersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsByName(Roles name) {
        return persistenceRepository.existsByName(name);
    }
}
