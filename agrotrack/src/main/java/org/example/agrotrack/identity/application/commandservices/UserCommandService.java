package org.example.agrotrack.identity.application.commandservices;

import org.example.agrotrack.identity.domain.model.aggregates.User;
import org.example.agrotrack.identity.domain.model.commands.CreateUserCommand;
import org.example.agrotrack.identity.domain.model.commands.UpdateUserCommand;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface UserCommandService {
    Result<User, ApplicationError> handle(CreateUserCommand command);
    Result<User, ApplicationError> handle(UpdateUserCommand command);
}
