package org.example.agrotrack.iam.application.internal.commandservices;

import org.example.agrotrack.iam.application.commandservices.SignInResult;
import org.example.agrotrack.iam.application.commandservices.UserCommandService;
import org.example.agrotrack.iam.application.internal.outboundservices.hashing.HashingService;
import org.example.agrotrack.iam.application.internal.outboundservices.tokens.TokenService;
import org.example.agrotrack.iam.application.queryservices.RoleQueryService;
import org.example.agrotrack.iam.domain.model.aggregates.User;
import org.example.agrotrack.iam.domain.model.commands.SignInCommand;
import org.example.agrotrack.iam.domain.model.commands.SignUpCommand;
import org.example.agrotrack.iam.domain.model.commands.UpdateCredentialsCommand;
import org.example.agrotrack.iam.domain.model.entities.Role;
import org.example.agrotrack.iam.domain.model.queries.GetRoleByNameQuery;
import org.example.agrotrack.iam.domain.repositories.UserRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("iamUserCommandServiceImpl")
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleQueryService roleQueryService;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService,
                                  TokenService tokenService, RoleQueryService roleQueryService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleQueryService = roleQueryService;
    }

    @Override
    public Result<SignInResult, ApplicationError> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty() || !hashingService.matches(command.password(), user.get().getPassword())) {
            return Result.failure(ApplicationError.invalidCredentials());
        }
        var token = tokenService.generateToken(user.get().getEmail());
        return Result.success(new SignInResult(user.get(), token));
    }

    @Override
    @Transactional
    public Result<SignInResult, ApplicationError> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            return Result.failure(ApplicationError.conflict("User", "Email already in use: " + command.email()));
        }

        List<Role> roles = command.roles().stream()
                .map(role -> roleQueryService.handle(new GetRoleByNameQuery(role.getName())))
                .flatMap(Optional::stream)
                .toList();
        if (roles.isEmpty()) {
            return Result.failure(ApplicationError.validationError("roles", "No valid roles provided"));
        }

        var user = User.create(command.email(), hashingService.encode(command.password()), roles);
        var saved = userRepository.save(user);
        var token = tokenService.generateToken(saved.getEmail());
        return Result.success(new SignInResult(saved, token));
    }

    @Override
    @Transactional
    public Result<User, ApplicationError> handle(UpdateCredentialsCommand command) {
        var userOpt = userRepository.findById(command.userId());
        if (userOpt.isEmpty()) {
            return Result.failure(ApplicationError.notFound("User", command.userId()));
        }

        var user = userOpt.get();
        var emailOwner = userRepository.findByEmail(command.email());
        if (emailOwner.isPresent() && !emailOwner.get().getId().equals(user.getId())) {
            return Result.failure(ApplicationError.conflict("User", "Email already in use: " + command.email()));
        }

        String encodedPassword = (command.password() == null || command.password().isBlank())
                ? user.getPassword()
                : hashingService.encode(command.password());
        user.updateCredentials(command.email(), encodedPassword);
        return Result.success(userRepository.save(user));
    }
}
