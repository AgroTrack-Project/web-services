package org.example.agrotrack.alerts.infrastructure.external.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents the response returned by the OpenWeather API.
 * Contains weather conditions, temperature data, humidity,
 * and the city name.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherResponse(
        List<WeatherCondition> weather,
        MainData main,
        String name
) {

    /**
     * Represents a weather condition entry provided by the API.
     *
     * @param description textual description of the weather condition
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record WeatherCondition(String description) {}

    /**
     * Represents the main weather measurements returned by the API.
     *
     * @param temp current temperature
     * @param tempMin minimum temperature
     * @param humidity current humidity percentage
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MainData(
            double temp,
            @JsonProperty("temp_min") double tempMin,
            double humidity
    ) {}
}