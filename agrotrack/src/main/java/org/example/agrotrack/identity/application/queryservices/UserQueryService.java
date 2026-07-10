package org.example.agrotrack.identity.application.queryservices;

import org.example.agrotrack.identity.domain.model.aggregates.User;
import org.example.agrotrack.identity.domain.model.queries.GetUserByIamUserIdQuery;
import org.example.agrotrack.identity.domain.model.queries.GetUserByIdQuery;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface UserQueryService {
    Result<User, ApplicationError> handle(GetUserByIdQuery query);
    Result<User, ApplicationError> handle(GetUserByIamUserIdQuery query);
}
