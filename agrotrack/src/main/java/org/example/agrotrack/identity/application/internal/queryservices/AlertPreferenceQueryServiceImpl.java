package org.example.agrotrack.identity.application.internal.queryservices;

import org.example.agrotrack.identity.application.queryservices.AlertPreferenceQueryService;
import org.example.agrotrack.identity.domain.model.aggregates.AlertPreference;
import org.example.agrotrack.identity.domain.model.queries.ListAlertPreferencesQuery;
import org.example.agrotrack.identity.domain.repositories.AlertPreferenceRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertPreferenceQueryServiceImpl implements AlertPreferenceQueryService {

    private final AlertPreferenceRepository repository;

    public AlertPreferenceQueryServiceImpl(AlertPreferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<List<AlertPreference>, ApplicationError> handle(ListAlertPreferencesQuery query) {
        if (query.userId() != null && !query.userId().isBlank()) {
            return repository.findByUserId(query.userId())
                    .map(p -> Result.<List<AlertPreference>, ApplicationError>success(List.of(p)))
                    .orElseGet(() -> Result.success(List.of()));
        }
        return Result.success(repository.findAll());
    }
}
