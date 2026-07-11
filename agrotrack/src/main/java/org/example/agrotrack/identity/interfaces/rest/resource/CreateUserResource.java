package org.example.agrotrack.identity.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserResource(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank @JsonProperty("iam_user_id") String iamUserId,
        @NotBlank @JsonProperty("plan_type") String planType,
        @JsonProperty("company_name") String companyName
) {}
