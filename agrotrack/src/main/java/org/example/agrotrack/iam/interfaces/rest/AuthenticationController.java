package org.example.agrotrack.iam.interfaces.rest;

import org.example.agrotrack.iam.application.commandservices.UserCommandService;
import org.example.agrotrack.iam.interfaces.rest.resource.AuthenticatedUserResource;
import org.example.agrotrack.iam.interfaces.rest.resource.SignInResource;
import org.example.agrotrack.iam.interfaces.rest.resource.SignUpResource;
import org.example.agrotrack.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import org.example.agrotrack.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import org.example.agrotrack.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Endpoints for signing in and issuing IAM credentials")
public class AuthenticationController {

    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInResource resource) {
        var result = userCommandService.handle(SignInCommandFromResourceAssembler.toCommandFromResource(resource));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                signInResult -> AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(signInResult.user(), signInResult.token()),
                HttpStatus.OK
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpResource resource) {
        var result = userCommandService.handle(SignUpCommandFromResourceAssembler.toCommandFromResource(resource));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                signInResult -> AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(signInResult.user(), signInResult.token()),
                HttpStatus.CREATED
        );
    }
}
