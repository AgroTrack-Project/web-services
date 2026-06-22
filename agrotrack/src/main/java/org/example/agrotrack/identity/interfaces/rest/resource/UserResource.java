package org.example.agrotrack.identity.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record UserResource(
        String id,
        String name,
        String email,
        String password,
        @JsonProperty("user_type") String userType,
        @JsonProperty("plan_type") String planType,
        @JsonProperty("company_name") String companyName,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {}
