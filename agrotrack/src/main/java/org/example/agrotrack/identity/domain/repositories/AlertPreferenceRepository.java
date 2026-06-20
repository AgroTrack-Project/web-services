package org.example.agrotrack.identity.domain.repositories;

import org.example.agrotrack.identity.domain.model.aggregates.AlertPreference;

import java.util.List;
import java.util.Optional;

public interface AlertPreferenceRepository {
    Optional<AlertPreference> findById(String id);
    Optional<AlertPreference> findByUserId(String userId);
    List<AlertPreference> findAll();
    AlertPreference save(AlertPreference preference);
}
