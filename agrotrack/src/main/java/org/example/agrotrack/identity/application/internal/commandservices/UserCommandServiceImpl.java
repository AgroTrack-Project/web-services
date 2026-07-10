package org.example.agrotrack.identity.application.internal.commandservices;

import org.example.agrotrack.iam.interfaces.acl.IamContextFacade;
import org.example.agrotrack.identity.application.commandservices.UserCommandService;
import org.example.agrotrack.identity.domain.model.aggregates.AlertPreference;
import org.example.agrotrack.identity.domain.model.aggregates.User;
import org.example.agrotrack.identity.domain.model.commands.CreateUserCommand;
import org.example.agrotrack.identity.domain.model.commands.UpdateUserCommand;
import org.example.agrotrack.identity.domain.model.valueobjects.PlanType;
import org.example.agrotrack.identity.domain.model.valueobjects.UserType;
import org.example.agrotrack.identity.domain.repositories.AlertPreferenceRepository;
import org.example.agrotrack.identity.domain.repositories.UserRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final AlertPreferenceRepository alertPreferenceRepository;
    private final IamContextFacade iamContextFacade;

    public UserCommandServiceImpl(UserRepository userRepository,
                                  AlertPreferenceRepository alertPreferenceRepository,
                                  IamContextFacade iamContextFacade) {
        this.userRepository = userRepository;
        this.alertPreferenceRepository = alertPreferenceRepository;
        this.iamContextFacade = iamContextFacade;
    }

    @Override
    @Transactional
    public Result<User, ApplicationError> handle(CreateUserCommand command) {
        try {
            if (userRepository.existsByEmail(command.email())) {
                return Result.failure(ApplicationError.conflict("User", "email already in use: " + command.email()));
            }

            if (!iamContextFacade.existsById(command.iamUserId())) {
                return Result.failure(ApplicationError.notFound("IamUser", command.iamUserId()));
            }
            if (userRepository.findByIamUserId(command.iamUserId()).isPresent()) {
                return Result.failure(ApplicationError.conflict("User", "profile already exists for this account"));
            }

            PlanType planType;
            try {
                planType = PlanType.valueOf(command.planType().toUpperCase());
            } catch (IllegalArgumentException e) {
                return Result.failure(ApplicationError.validationError("planType", e.getMessage()));
            }
            UserType userType = userTypeFor(planType);

            User user = User.create(command.name(), command.email(), command.iamUserId(),
                    userType, planType, command.companyName());
            User saved = userRepository.save(user);

            AlertPreference preference = AlertPreference.createDefault(saved.getId());
            alertPreferenceRepository.save(preference);

            return Result.success(saved);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("User", e.getMessage()));
        }
    }

    @Override
    @Transactional
    public Result<User, ApplicationError> handle(UpdateUserCommand command) {
        try {
            var userOpt = userRepository.findById(command.userId());
            if (userOpt.isEmpty()) {
                return Result.failure(ApplicationError.notFound("User", command.userId()));
            }

            PlanType planType;
            try {
                planType = PlanType.valueOf(command.planType().toUpperCase());
            } catch (IllegalArgumentException e) {
                return Result.failure(ApplicationError.validationError("planType", e.getMessage()));
            }

            User user = userOpt.get();
            boolean credentialsUpdated = iamContextFacade.updateCredentials(user.getIamUserId(), command.email(), command.password());
            if (!credentialsUpdated) {
                return Result.failure(ApplicationError.conflict("User", "email already registered for authentication: " + command.email()));
            }

            user.update(command.name(), command.email(), planType, command.companyName());
            return Result.success(userRepository.save(user));
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("User", e.getMessage()));
        }
    }

    private static UserType userTypeFor(PlanType planType) {
        return switch (planType) {
            case BASIC, PRO -> UserType.FARMER;
            case ENTERPRISE -> UserType.AGRICULTURAL_MANAGER;
        };
    }
}
