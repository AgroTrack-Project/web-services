package org.example.agrotrack.identity.interfaces.rest.transform;

import org.example.agrotrack.identity.domain.model.commands.CreateUserCommand;
import org.example.agrotrack.identity.interfaces.rest.resource.CreateUserResource;

public final class CreateUserCommandFromResourceAssembler {

    private CreateUserCommandFromResourceAssembler() {}

    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.name(),
                resource.email(),
                resource.iamUserId(),
                resource.planType(),
                resource.companyName()
        );
    }
}
