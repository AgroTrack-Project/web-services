package org.example.agrotrack.iam.application.acl;

import org.example.agrotrack.iam.application.commandservices.SignInResult;
import org.example.agrotrack.iam.application.commandservices.UserCommandService;
import org.example.agrotrack.iam.application.queryservices.RoleQueryService;
import org.example.agrotrack.iam.application.queryservices.UserQueryService;
import org.example.agrotrack.iam.domain.model.aggregates.User;
import org.example.agrotrack.iam.domain.model.commands.SignUpCommand;
import org.example.agrotrack.iam.domain.model.commands.UpdateCredentialsCommand;
import org.example.agrotrack.iam.domain.model.queries.GetRoleByNameQuery;
import org.example.agrotrack.iam.domain.model.queries.GetUserByEmailQuery;
import org.example.agrotrack.iam.domain.model.queries.GetUserByIdQuery;
import org.example.agrotrack.iam.domain.model.valueobjects.Roles;
import org.example.agrotrack.iam.interfaces.acl.IamContextFacade;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IamContextFacadeImpl implements IamContextFacade {

    private final UserCommandService userCommandService;
    private final RoleQueryService roleQueryService;
    private final UserQueryService userQueryService;

    public IamContextFacadeImpl(UserCommandService userCommandService, RoleQueryService roleQueryService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.roleQueryService = roleQueryService;
        this.userQueryService = userQueryService;
    }

    @Override
    public boolean existsById(String iamUserId) {
        return userQueryService.handle(new GetUserByIdQuery(iamUserId)).isPresent();
    }

    @Override
    public Optional<String> findIdByEmail(String email) {
        return userQueryService.handle(new GetUserByEmailQuery(email)).map(User::getId);
    }

    @Override
    public Optional<String> createUser(String email, String rawPassword, String roleName) {
        try {
            var roleOpt = roleQueryService.handle(new GetRoleByNameQuery(Roles.valueOf(roleName)));
            if (roleOpt.isEmpty()) {
                return Optional.empty();
            }
            Result<SignInResult, ApplicationError> result = userCommandService.handle(new SignUpCommand(email, rawPassword, List.of(roleOpt.get())));
            return switch (result) {
                case Result.Success<SignInResult, ApplicationError> success -> Optional.ofNullable(success.value().user().getId());
                case Result.Failure<SignInResult, ApplicationError> ignored -> Optional.empty();
            };
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean updateCredentials(String iamUserId, String email, String rawPassword) {
        var result = userCommandService.handle(new UpdateCredentialsCommand(iamUserId, email, rawPassword));
        return result.isSuccess();
    }
}
