package org.example.agrotrack.iam.interfaces.rest.transform;

import org.example.agrotrack.iam.domain.model.aggregates.User;
import org.example.agrotrack.iam.domain.model.entities.Role;
import org.example.agrotrack.iam.interfaces.rest.resource.UserResource;

public final class UserResourceFromEntityAssembler {

    private UserResourceFromEntityAssembler() {}

    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getEmail(), roles);
    }
}
