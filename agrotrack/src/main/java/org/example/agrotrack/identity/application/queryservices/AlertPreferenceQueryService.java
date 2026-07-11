package org.example.agrotrack.identity.application.queryservices;

import org.example.agrotrack.identity.domain.model.aggregates.AlertPreference;
import org.example.agrotrack.identity.domain.model.queries.ListAlertPreferencesQuery;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

import java.util.List;

public interface AlertPreferenceQueryService {
    Result<List<AlertPreference>, ApplicationError> handle(ListAlertPreferencesQuery query);
}
