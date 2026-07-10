package org.example.agrotrack.iam.interfaces.rest.transform;

import org.example.agrotrack.iam.domain.model.commands.SignInCommand;
import org.example.agrotrack.iam.interfaces.rest.resource.SignInResource;

public final class SignInCommandFromResourceAssembler {

    private SignInCommandFromResourceAssembler() {}

    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.email(), resource.password());
    }
}
