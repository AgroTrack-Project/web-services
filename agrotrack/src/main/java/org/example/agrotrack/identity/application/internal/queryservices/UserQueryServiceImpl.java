package org.example.agrotrack.identity.application.internal.queryservices;

import org.example.agrotrack.identity.application.queryservices.UserQueryService;
import org.example.agrotrack.identity.domain.model.aggregates.User;
import org.example.agrotrack.identity.domain.model.queries.GetUserByIamUserIdQuery;
import org.example.agrotrack.identity.domain.model.queries.GetUserByIdQuery;
import org.example.agrotrack.identity.domain.repositories.UserRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository repository;

    public UserQueryServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<User, ApplicationError> handle(GetUserByIdQuery query) {
        return repository.findById(query.userId())
                .map(Result::<User, ApplicationError>success)
                .orElseGet(() -> Result.failure(ApplicationError.notFound("User", query.userId())));
    }

    @Override
    public Result<User, ApplicationError> handle(GetUserByIamUserIdQuery query) {
        return repository.findByIamUserId(query.iamUserId())
                .map(Result::<User, ApplicationError>success)
                .orElseGet(() -> Result.failure(ApplicationError.notFound("User", query.iamUserId())));
    }
}
