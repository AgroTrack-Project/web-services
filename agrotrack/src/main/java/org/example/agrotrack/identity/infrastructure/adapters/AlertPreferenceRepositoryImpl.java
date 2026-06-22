package org.example.agrotrack.identity.infrastructure.adapters;

import org.example.agrotrack.identity.domain.model.aggregates.AlertPreference;
import org.example.agrotrack.identity.domain.repositories.AlertPreferenceRepository;
import org.example.agrotrack.identity.infrastructure.assemblers.AlertPreferencePersistenceAssembler;
import org.example.agrotrack.identity.infrastructure.entities.AlertPreferencePersistenceEntity;
import org.example.agrotrack.identity.infrastructure.repositories.AlertPreferencePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlertPreferenceRepositoryImpl implements AlertPreferenceRepository {

    private final AlertPreferencePersistenceRepository persistenceRepository;

    public AlertPreferenceRepositoryImpl(AlertPreferencePersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<AlertPreference> findById(String id) {
        return persistenceRepository.findById(id).map(AlertPreferencePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<AlertPreference> findByUserId(String userId) {
        return persistenceRepository.findByUserId(userId).map(AlertPreferencePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<AlertPreference> findAll() {
        return persistenceRepository.findAll().stream()
                .map(AlertPreferencePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public AlertPreference save(AlertPreference preference) {
        if (preference.getId() == null) {
            AlertPreferencePersistenceEntity entity = AlertPreferencePersistenceAssembler.toPersistenceFromDomain(preference);
            return AlertPreferencePersistenceAssembler.toDomainFromPersistence(persistenceRepository.save(entity));
        }
        AlertPreferencePersistenceEntity entity = persistenceRepository.findById(preference.getId())
                .orElseThrow(() -> new IllegalStateException("AlertPreference not found: " + preference.getId()));
        AlertPreferencePersistenceAssembler.updatePersistenceFromDomain(entity, preference);
        return AlertPreferencePersistenceAssembler.toDomainFromPersistence(persistenceRepository.save(entity));
    }
}
