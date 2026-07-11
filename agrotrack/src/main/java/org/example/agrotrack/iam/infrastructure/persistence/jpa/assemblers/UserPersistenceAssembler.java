package org.example.agrotrack.iam.infrastructure.persistence.jpa.assemblers;

import org.example.agrotrack.iam.domain.model.aggregates.User;
import org.example.agrotrack.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;

import java.util.HashSet;
import java.util.stream.Collectors;

public final class UserPersistenceAssembler {

    private UserPersistenceAssembler() {}

    public static User toDomainFromPersistence(UserPersistenceEntity entity) {
        if (entity == null) return null;
        var roles = entity.getRoles().stream()
                .map(RolePersistenceAssembler::toDomainFromPersistence)
                .collect(Collectors.toSet());
        return User.restore(entity.getId(), entity.getEmail(), entity.getPassword(), roles);
    }

    public static UserPersistenceEntity toPersistenceFromDomain(User user) {
        var entity = new UserPersistenceEntity();
        applyDomainToPersistence(entity, user);
        return entity;
    }

    public static void updatePersistenceFromDomain(UserPersistenceEntity entity, User user) {
        applyDomainToPersistence(entity, user);
    }

    private static void applyDomainToPersistence(UserPersistenceEntity entity, User user) {
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRoles(user.getRoles() == null
                ? new HashSet<>()
                : user.getRoles().stream()
                        .map(RolePersistenceAssembler::toPersistenceFromDomain)
                        .collect(Collectors.toSet()));
    }
}
