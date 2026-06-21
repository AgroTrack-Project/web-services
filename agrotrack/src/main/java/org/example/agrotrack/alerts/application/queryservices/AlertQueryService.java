package org.example.agrotrack.alerts.application.queryservices;

import org.example.agrotrack.alerts.domain.model.aggregates.Alert;
import org.example.agrotrack.alerts.domain.model.queries.GetAlertsByCityQuery;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

import java.util.List;

public interface AlertQueryService {
    Result<List<Alert>, ApplicationError> handle(GetAlertsByCityQuery query);
}
