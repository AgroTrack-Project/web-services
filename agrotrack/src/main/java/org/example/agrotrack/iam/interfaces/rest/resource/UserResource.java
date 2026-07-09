package org.example.agrotrack.iam.interfaces.rest.resource;

import java.util.List;

public record UserResource(
        String id,
        String email,
        List<String> roles
) {}
