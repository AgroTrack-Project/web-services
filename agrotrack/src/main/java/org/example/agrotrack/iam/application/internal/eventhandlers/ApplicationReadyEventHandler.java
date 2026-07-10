package org.example.agrotrack.iam.application.internal.eventhandlers;

import lombok.extern.slf4j.Slf4j;
import org.example.agrotrack.iam.application.commandservices.RoleCommandService;
import org.example.agrotrack.iam.domain.model.commands.SeedRolesCommand;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationReadyEventHandler {

    private final RoleCommandService roleCommandService;

    public ApplicationReadyEventHandler(RoleCommandService roleCommandService) {
        this.roleCommandService = roleCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        log.info("Verifying if IAM roles seeding is needed");
        roleCommandService.handle(new SeedRolesCommand());
        log.info("IAM roles seeding verification finished");
    }
}
