package org.example.agrotrack.iam.application.internal.commandservices;

import org.example.agrotrack.iam.application.commandservices.RoleCommandService;
import org.example.agrotrack.iam.domain.model.commands.SeedRolesCommand;
import org.example.agrotrack.iam.domain.model.entities.Role;
import org.example.agrotrack.iam.domain.model.valueobjects.Roles;
import org.example.agrotrack.iam.domain.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(role));
            }
        });
    }
}
