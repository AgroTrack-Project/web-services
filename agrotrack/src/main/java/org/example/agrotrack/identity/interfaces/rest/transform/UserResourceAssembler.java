package org.example.agrotrack.identity.interfaces.rest.transform;

import org.example.agrotrack.identity.domain.model.aggregates.User;
import org.example.agrotrack.identity.interfaces.rest.resource.UserResource;

public final class UserResourceAssembler {

    private UserResourceAssembler() {}

    public static UserResource toResource(User user) {
        return new UserResource(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType().name().toLowerCase(),
                user.getPlanType().name(),
                user.getCompanyName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
