package org.example.agrotrack.payments.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentIntentCreatedResource(
        @JsonProperty("client_secret") String clientSecret
) {}
