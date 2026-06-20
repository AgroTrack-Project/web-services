package org.example.agrotrack.identity.infrastructure.assemblers;

import org.example.agrotrack.identity.domain.model.aggregates.AlertPreference;
import org.example.agrotrack.identity.infrastructure.entities.AlertPreferencePersistenceEntity;

public final class AlertPreferencePersistenceAssembler {

    private AlertPreferencePersistenceAssembler() {}

    public static AlertPreference toDomainFromPersistence(AlertPreferencePersistenceEntity entity) {
        return AlertPreference.restore(
                entity.getId(),
                entity.getUserId(),
                entity.isFrostEnabled(),
                entity.isDroughtEnabled(),
                entity.isHeavyRainEnabled()
        );
    }

    public static AlertPreferencePersistenceEntity toPersistenceFromDomain(AlertPreference preference) {
        AlertPreferencePersistenceEntity entity = new AlertPreferencePersistenceEntity();
        entity.setUserId(preference.getUserId());
        entity.setFrostEnabled(preference.isFrostEnabled());
        entity.setDroughtEnabled(preference.isDroughtEnabled());
        entity.setHeavyRainEnabled(preference.isHeavyRainEnabled());
        return entity;
    }

    public static void updatePersistenceFromDomain(AlertPreferencePersistenceEntity entity, AlertPreference preference) {
        entity.setFrostEnabled(preference.isFrostEnabled());
        entity.setDroughtEnabled(preference.isDroughtEnabled());
        entity.setHeavyRainEnabled(preference.isHeavyRainEnabled());
    }
}
