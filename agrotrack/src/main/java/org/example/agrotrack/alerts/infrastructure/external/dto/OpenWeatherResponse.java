package org.example.agrotrack.alerts.infrastructure.external.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherResponse(
        List<WeatherCondition> weather,
        MainData main,
        String name
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record WeatherCondition(String description) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MainData(
            double temp,
            @JsonProperty("temp_min") double tempMin,
            double humidity
    ) {}
}
