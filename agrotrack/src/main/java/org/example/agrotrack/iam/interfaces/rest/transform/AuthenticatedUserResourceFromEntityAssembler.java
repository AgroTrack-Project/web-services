package org.example.agrotrack.iam.interfaces.rest.transform;

import org.example.agrotrack.iam.domain.model.aggregates.User;
import org.example.agrotrack.iam.domain.model.entities.Role;
import org.example.agrotrack.iam.interfaces.rest.resource.AuthenticatedUserResource;

public final class AuthenticatedUserResourceFromEntityAssembler {

    private AuthenticatedUserResourceFromEntityAssembler() {}

    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new AuthenticatedUserResource(user.getId(), user.getEmail(), roles, token);
    }
}
