package org.example.agrotrack.iam.interfaces.rest.resource;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignInResource(
        @NotBlank @Email String email,
        @NotBlank String password
) {}
