package org.example.agrotrack.iam.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpResource(
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank @JsonProperty("plan") String plan
) {}
