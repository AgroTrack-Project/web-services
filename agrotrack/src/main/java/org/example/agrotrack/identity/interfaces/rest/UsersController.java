package org.example.agrotrack.identity.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.agrotrack.iam.interfaces.acl.IamContextFacade;
import org.example.agrotrack.identity.application.commandservices.UserCommandService;
import org.example.agrotrack.identity.application.queryservices.UserQueryService;
import org.example.agrotrack.identity.domain.model.queries.GetUserByIamUserIdQuery;
import org.example.agrotrack.identity.domain.model.queries.GetUserByIdQuery;
import org.example.agrotrack.identity.interfaces.rest.resource.CreateUserResource;
import org.example.agrotrack.identity.interfaces.rest.resource.UpdateUserResource;
import org.example.agrotrack.identity.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import org.example.agrotrack.identity.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import org.example.agrotrack.identity.interfaces.rest.transform.UserResourceAssembler;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Endpoints for managing users")
@RequiredArgsConstructor
public class UsersController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final IamContextFacade iamContextFacade;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                userQueryService.handle(new GetUserByIdQuery(id)),
                UserResourceAssembler::toResource,
                HttpStatus.OK
        );
    }

    @GetMapping("/by-iam-user/{iamUserId}")
    public ResponseEntity<?> getByIamUserId(@PathVariable String iamUserId) {
        var callerEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        var callerIamUserId = iamContextFacade.findIdByEmail(callerEmail);
        if (callerIamUserId.isEmpty() || !callerIamUserId.get().equals(iamUserId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntityAssembler.toResponseEntityFromResult(
                userQueryService.handle(new GetUserByIamUserIdQuery(iamUserId)),
                UserResourceAssembler::toResource,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                userCommandService.handle(CreateUserCommandFromResourceAssembler.toCommandFromResource(resource)),
                UserResourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UpdateUserResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                userCommandService.handle(UpdateUserCommandFromResourceAssembler.toCommandFromResource(id, resource)),
                UserResourceAssembler::toResource,
                HttpStatus.OK
        );
    }
}
