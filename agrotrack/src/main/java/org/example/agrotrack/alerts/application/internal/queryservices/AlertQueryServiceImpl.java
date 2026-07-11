package org.example.agrotrack.alerts.application.internal.queryservices;

import org.example.agrotrack.alerts.application.queryservices.AlertQueryService;
import org.example.agrotrack.alerts.domain.model.aggregates.Alert;
import org.example.agrotrack.alerts.domain.model.queries.GetAlertsByCityQuery;
import org.example.agrotrack.alerts.domain.ports.WeatherServicePort;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertQueryServiceImpl implements AlertQueryService {

    private final WeatherServicePort weatherServicePort;

    public AlertQueryServiceImpl(WeatherServicePort weatherServicePort) {
        this.weatherServicePort = weatherServicePort;
    }

    @Override
    public Result<List<Alert>, ApplicationError> handle(GetAlertsByCityQuery query) {
        if (query.city() == null || query.city().isBlank()) {
            return Result.failure(ApplicationError.validationError("city", "City name is required"));
        }

        return weatherServicePort.fetchAlertsForCity(query.city().trim());
    }
}