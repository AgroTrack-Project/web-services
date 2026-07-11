package org.example.agrotrack.iam.application.queryservices;

import org.example.agrotrack.iam.domain.model.aggregates.User;
import org.example.agrotrack.iam.domain.model.queries.GetAllUsersQuery;
import org.example.agrotrack.iam.domain.model.queries.GetUserByEmailQuery;
import org.example.agrotrack.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);

    Optional<User> handle(GetUserByIdQuery query);

    Optional<User> handle(GetUserByEmailQuery query);
}
