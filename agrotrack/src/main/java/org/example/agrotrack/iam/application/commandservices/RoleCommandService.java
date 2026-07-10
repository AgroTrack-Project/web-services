package org.example.agrotrack.iam.application.commandservices;

import org.example.agrotrack.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
