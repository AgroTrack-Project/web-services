package org.example.agrotrack.iam.interfaces.rest.transform;

import org.example.agrotrack.iam.domain.model.entities.Role;
import org.example.agrotrack.iam.interfaces.rest.resource.RoleResource;

public final class RoleResourceFromEntityAssembler {

    private RoleResourceFromEntityAssembler() {}

    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
