package org.example.agrotrack.payments.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CreatePaymentIntentResource(
        @NotBlank @JsonProperty("plan_type") String planType
) {}
