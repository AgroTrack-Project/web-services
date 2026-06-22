package org.example.agrotrack.identity.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateAlertPreferenceResource(
        @JsonProperty("frost_enabled") boolean frostEnabled,
        @JsonProperty("drought_enabled") boolean droughtEnabled,
        @JsonProperty("heavy_rain_enabled") boolean heavyRainEnabled
) {}
