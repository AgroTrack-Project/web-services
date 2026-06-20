package org.example.agrotrack.identity.interfaces.rest.transform;

import org.example.agrotrack.identity.domain.model.aggregates.AlertPreference;
import org.example.agrotrack.identity.interfaces.rest.resource.AlertPreferenceResource;

public final class AlertPreferenceResourceAssembler {

    private AlertPreferenceResourceAssembler() {}

    public static AlertPreferenceResource toResource(AlertPreference preference) {
        return new AlertPreferenceResource(
                preference.getId(),
                preference.getUserId(),
                preference.isFrostEnabled(),
                preference.isDroughtEnabled(),
                preference.isHeavyRainEnabled()
        );
    }
}
