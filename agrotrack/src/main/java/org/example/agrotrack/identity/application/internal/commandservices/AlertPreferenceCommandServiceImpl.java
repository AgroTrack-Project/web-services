package org.example.agrotrack.identity.application.internal.commandservices;

import org.example.agrotrack.identity.application.commandservices.AlertPreferenceCommandService;
import org.example.agrotrack.identity.domain.model.aggregates.AlertPreference;
import org.example.agrotrack.identity.domain.model.commands.UpdateAlertPreferenceCommand;
import org.example.agrotrack.identity.domain.repositories.AlertPreferenceRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlertPreferenceCommandServiceImpl implements AlertPreferenceCommandService {

    private final AlertPreferenceRepository repository;

    public AlertPreferenceCommandServiceImpl(AlertPreferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Result<AlertPreference, ApplicationError> handle(UpdateAlertPreferenceCommand command) {
        var preferenceOpt = repository.findById(command.preferenceId());
        if (preferenceOpt.isEmpty()) {
            return Result.failure(ApplicationError.notFound("AlertPreference", command.preferenceId()));
        }

        AlertPreference preference = preferenceOpt.get();
        preference.update(command.frostEnabled(), command.droughtEnabled(), command.heavyRainEnabled());
        return Result.success(repository.save(preference));
    }
}
