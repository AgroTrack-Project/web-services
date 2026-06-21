package org.example.agrotrack.alerts.domain.ports;

import org.example.agrotrack.alerts.domain.model.aggregates.Alert;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

import java.util.List;

public interface WeatherServicePort {
    Result<List<Alert>, ApplicationError> fetchAlertsForCity(String city);
}
