package org.example.agrotrack.identity.interfaces.rest.transform;

import org.example.agrotrack.identity.domain.model.commands.UpdateUserCommand;
import org.example.agrotrack.identity.interfaces.rest.resource.UpdateUserResource;

public final class UpdateUserCommandFromResourceAssembler {

    private UpdateUserCommandFromResourceAssembler() {}

    public static UpdateUserCommand toCommandFromResource(String userId, UpdateUserResource resource) {
        return new UpdateUserCommand(
                userId,
                resource.name(),
                resource.email(),
                resource.password(),
                resource.planType(),
                resource.companyName()
        );
    }
}
