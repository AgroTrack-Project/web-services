package org.example.agrotrack.alerts.application.queryservices;

import org.example.agrotrack.alerts.domain.model.aggregates.Alert;
import org.example.agrotrack.alerts.domain.model.queries.GetAlertsByCityQuery;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

import java.util.List;

/**
 * Defines query operations related to weather alerts.
 */
public interface AlertQueryService {

    /**
     * Retrieves weather alerts for the specified city.
     *
     * @param query query containing the city name
     * @return a result containing the alerts or an application error
     */
    Result<List<Alert>, ApplicationError> handle(GetAlertsByCityQuery query);
}