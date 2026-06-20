package org.example.agrotrack.identity.interfaces.rest.transform;

import org.example.agrotrack.identity.domain.model.commands.UpdateAlertPreferenceCommand;
import org.example.agrotrack.identity.interfaces.rest.resource.UpdateAlertPreferenceResource;

public final class UpdateAlertPreferenceCommandFromResourceAssembler {

    private UpdateAlertPreferenceCommandFromResourceAssembler() {}

    public static UpdateAlertPreferenceCommand toCommandFromResource(String preferenceId, UpdateAlertPreferenceResource resource) {
        return new UpdateAlertPreferenceCommand(
                preferenceId,
                resource.frostEnabled(),
                resource.droughtEnabled(),
                resource.heavyRainEnabled()
        );
    }
}
