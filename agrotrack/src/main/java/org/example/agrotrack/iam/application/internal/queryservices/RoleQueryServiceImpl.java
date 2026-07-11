package org.example.agrotrack.iam.application.internal.queryservices;

import org.example.agrotrack.iam.application.queryservices.RoleQueryService;
import org.example.agrotrack.iam.domain.model.entities.Role;
import org.example.agrotrack.iam.domain.model.queries.GetAllRolesQuery;
import org.example.agrotrack.iam.domain.model.queries.GetRoleByNameQuery;
import org.example.agrotrack.iam.domain.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {

    private final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }
}
