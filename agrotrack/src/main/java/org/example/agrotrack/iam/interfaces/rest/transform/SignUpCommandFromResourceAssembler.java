package org.example.agrotrack.iam.interfaces.rest.transform;

import org.example.agrotrack.iam.domain.model.commands.SignUpCommand;
import org.example.agrotrack.iam.domain.model.entities.Role;
import org.example.agrotrack.iam.domain.model.valueobjects.Roles;
import org.example.agrotrack.iam.interfaces.rest.resource.SignUpResource;

import java.util.List;

public final class SignUpCommandFromResourceAssembler {

    private SignUpCommandFromResourceAssembler() {}

    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var role = resolveRoleFromPlan(resource.plan());
        return new SignUpCommand(resource.email(), resource.password(), List.of(role));
    }

    private static Role resolveRoleFromPlan(String plan) {
        return switch (plan.trim().toUpperCase()) {
            case "BASIC", "PRO" -> new Role(Roles.ROLE_FARMER);
            case "ENTERPRISE" -> new Role(Roles.ROLE_AGRICULTURAL_MANAGER);
            default -> throw new IllegalArgumentException("Unknown plan: " + plan);
        };
    }
}
