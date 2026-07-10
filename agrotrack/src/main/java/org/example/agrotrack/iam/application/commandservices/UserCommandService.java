package org.example.agrotrack.iam.application.commandservices;

import org.example.agrotrack.iam.domain.model.aggregates.User;
import org.example.agrotrack.iam.domain.model.commands.SignInCommand;
import org.example.agrotrack.iam.domain.model.commands.SignUpCommand;
import org.example.agrotrack.iam.domain.model.commands.UpdateCredentialsCommand;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface UserCommandService {
    Result<SignInResult, ApplicationError> handle(SignInCommand command);

    Result<SignInResult, ApplicationError> handle(SignUpCommand command);

    Result<User, ApplicationError> handle(UpdateCredentialsCommand command);
}
