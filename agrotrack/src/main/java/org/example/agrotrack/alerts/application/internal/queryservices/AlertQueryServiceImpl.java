package org.example.agrotrack.alerts.application.internal.queryservices;

import org.example.agrotrack.alerts.application.queryservices.AlertQueryService;
import org.example.agrotrack.alerts.domain.model.aggregates.Alert;
import org.example.agrotrack.alerts.domain.model.queries.GetAlertsByCityQuery;
import org.example.agrotrack.alerts.domain.ports.WeatherServicePort;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Query service responsible for retrieving weather alerts for a specific city.
 * Validates the query data and delegates alert retrieval to the weather service port.
 */
@Service
public class AlertQueryServiceImpl implements AlertQueryService {

    private final WeatherServicePort weatherServicePort;

    /**
     * Creates a new query service instance.
     *
     * @param weatherServicePort port used to retrieve weather alerts
     */
    public AlertQueryServiceImpl(WeatherServicePort weatherServicePort) {
        this.weatherServicePort = weatherServicePort;
    }

    /**
     * Handles the request for retrieving weather alerts by city.
     *
     * @param query query containing the city name
     * @return a result containing the list of alerts or a validation error
     */
    @Override
    public Result<List<Alert>, ApplicationError> handle(GetAlertsByCityQuery query) {
        if (query.city() == null || query.city().isBlank()) {
            return Result.failure(ApplicationError.validationError("city", "City name is required"));
        }

        return weatherServicePort.fetchAlertsForCity(query.city().trim());
    }
}