package org.example.agrotrack.iam.domain.model.entities;

import org.example.agrotrack.iam.domain.model.valueobjects.Roles;

import java.util.List;
import java.util.Objects;

public class Role {

    private final String id;
    private final Roles name;

    public Role(String id, Roles name) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    public Role(Roles name) {
        this(null, name);
    }

    public String getId() {
        return id;
    }

    public Roles getName() {
        return name;
    }

    public String getStringName() {
        return name.name();
    }

    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(new Role(Roles.ROLE_FARMER));
        }
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return name == role.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
