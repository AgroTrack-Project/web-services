package org.example.agrotrack.alerts.domain.ports;

import org.example.agrotrack.alerts.domain.model.aggregates.Alert;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

import java.util.List;

/**
 * Defines the contract for retrieving weather alerts from an external weather service.
 */
public interface WeatherServicePort {

    /**
     * Retrieves weather alerts for the specified city.
     *
     * @param city the city for which alerts are requested
     * @return a result containing the alerts or an application error
     */
    Result<List<Alert>, ApplicationError> fetchAlertsForCity(String city);
}