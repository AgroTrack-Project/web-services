package org.example.agrotrack.iam.interfaces.rest.resource;

import java.util.List;

public record AuthenticatedUserResource(
        String id,
        String email,
        List<String> roles,
        String token
) {}
