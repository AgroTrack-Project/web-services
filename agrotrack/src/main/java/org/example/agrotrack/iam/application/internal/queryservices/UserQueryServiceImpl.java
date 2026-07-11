package org.example.agrotrack.iam.application.internal.queryservices;

import org.example.agrotrack.iam.application.queryservices.UserQueryService;
import org.example.agrotrack.iam.domain.model.aggregates.User;
import org.example.agrotrack.iam.domain.model.queries.GetAllUsersQuery;
import org.example.agrotrack.iam.domain.model.queries.GetUserByEmailQuery;
import org.example.agrotrack.iam.domain.model.queries.GetUserByIdQuery;
import org.example.agrotrack.iam.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("iamUserQueryServiceImpl")
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail(query.email());
    }
}
