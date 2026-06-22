package org.example.agrotrack.identity.application.commandservices;

import org.example.agrotrack.identity.domain.model.aggregates.AlertPreference;
import org.example.agrotrack.identity.domain.model.commands.UpdateAlertPreferenceCommand;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface AlertPreferenceCommandService {
    Result<AlertPreference, ApplicationError> handle(UpdateAlertPreferenceCommand command);
}
