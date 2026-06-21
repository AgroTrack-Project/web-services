package org.example.agrotrack.alerts.infrastructure.external.adapters;

import org.example.agrotrack.alerts.domain.model.aggregates.Alert;
import org.example.agrotrack.alerts.domain.model.valueobjects.AlertUrgency;
import org.example.agrotrack.alerts.domain.ports.WeatherServicePort;
import org.example.agrotrack.alerts.infrastructure.external.dto.OpenWeatherResponse;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenWeatherAdapter implements WeatherServicePort {

    private final RestClient restClient;

    @Value("${openweather.api.url}")
    private String apiUrl;

    @Value("${openweather.api.key}")
    private String apiKey;

    public OpenWeatherAdapter() {
        this.restClient = RestClient.create();
    }

    @Override
    public Result<List<Alert>, ApplicationError> fetchAlertsForCity(String city) {
        try {
            String url = "%s/weather?q=%s&appid=%s&units=metric".formatted(apiUrl, city, apiKey);

            OpenWeatherResponse response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(OpenWeatherResponse.class);

            if (response == null) {
                return Result.failure(ApplicationError.unexpected("OpenWeather", "Empty response received from weather service"));
            }

            return Result.success(toAlerts(response));

        } catch (RestClientResponseException e) {
            if (e.getStatusCode().value() == 404) {
                return Result.failure(ApplicationError.notFound("City", city));
            }
            return Result.failure(ApplicationError.unexpected("OpenWeather", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("OpenWeather", e.getMessage()));
        }
    }

    private List<Alert> toAlerts(OpenWeatherResponse response) {
        List<Alert> alerts = new ArrayList<>();

        String cityName = response.name();
        String description = (response.weather() != null && !response.weather().isEmpty())
                ? response.weather().get(0).description()
                : "";
        double humidity = response.main().humidity();
        double temp = response.main().temp();
        double tempMin = response.main().tempMin();

        if ("High".equals(calculateRainRisk(humidity, description))) {
            alerts.add(new Alert(cityName, "Heavy Rain Risk",
                    "Possible heavy rain or storms detected.", AlertUrgency.HIGH));
        }

        if ("High".equals(calculateDroughtRisk(humidity, temp))) {
            alerts.add(new Alert(cityName, "Drought Risk",
                    "Low humidity and high temperatures detected.", AlertUrgency.HIGH));
        }

        switch (calculateHeatRisk(temp, tempMin)) {
            case "High" -> alerts.add(new Alert(cityName, "Extreme Heat",
                    "Very high temperatures detected.", AlertUrgency.HIGH));
            case "Medium" -> alerts.add(new Alert(cityName, "Moderate Heat",
                    "Warm temperatures detected.", AlertUrgency.MEDIUM));
            case "Cold Alert" -> alerts.add(new Alert(cityName, "Cold Alert",
                    "Very low temperatures detected. Protect your crops from frost.", AlertUrgency.HIGH));
        }

        return alerts;
    }

    private String calculateRainRisk(double humidity, String description) {
        String text = description.toLowerCase();
        if (text.contains("rain") || text.contains("storm")
                || text.contains("drizzle") || text.contains("thunderstorm")) {
            return "High";
        }
        if (humidity >= 85) return "Medium";
        return "Low";
    }

    private String calculateDroughtRisk(double humidity, double temp) {
        if (humidity <= 30 && temp >= 30) return "High";
        if (humidity <= 50 && temp >= 24) return "Medium";
        return "Low";
    }

    private String calculateHeatRisk(double temp, double tempMin) {
        if (tempMin < 5) return "Cold Alert";
        if (temp >= 35) return "High";
        if (temp >= 28) return "Medium";
        return "Low";
    }
}
