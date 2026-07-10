package org.example.agrotrack.iam.interfaces.rest;

import org.example.agrotrack.iam.application.queryservices.RoleQueryService;
import org.example.agrotrack.iam.domain.model.queries.GetAllRolesQuery;
import org.example.agrotrack.iam.interfaces.rest.transform.RoleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Endpoints for listing IAM roles")
public class RolesController {

    private final RoleQueryService roleQueryService;

    public RolesController(RoleQueryService roleQueryService) {
        this.roleQueryService = roleQueryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        var roles = roleQueryService.handle(new GetAllRolesQuery());
        return ResponseEntity.ok(roles.stream().map(RoleResourceFromEntityAssembler::toResourceFromEntity).toList());
    }
}
