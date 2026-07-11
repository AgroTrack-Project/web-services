package org.example.agrotrack.identity.infrastructure.assemblers;

import org.example.agrotrack.identity.domain.model.aggregates.User;
import org.example.agrotrack.identity.infrastructure.entities.UserPersistenceEntity;

public final class UserPersistenceAssembler {

    private UserPersistenceAssembler() {}

    public static User toDomainFromPersistence(UserPersistenceEntity entity) {
        return User.restore(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getIamUserId(),
                entity.getUserType(),
                entity.getPlanType(),
                entity.getCompanyName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static UserPersistenceEntity toPersistenceFromDomain(User user) {
        UserPersistenceEntity entity = new UserPersistenceEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setIamUserId(user.getIamUserId());
        entity.setUserType(user.getUserType());
        entity.setPlanType(user.getPlanType());
        entity.setCompanyName(user.getCompanyName());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        return entity;
    }

    public static void updatePersistenceFromDomain(UserPersistenceEntity entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPlanType(user.getPlanType());
        entity.setCompanyName(user.getCompanyName());
        entity.setUpdatedAt(user.getUpdatedAt());
    }
}
