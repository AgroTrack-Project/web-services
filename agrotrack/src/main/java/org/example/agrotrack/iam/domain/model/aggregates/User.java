package org.example.agrotrack.iam.domain.model.aggregates;

import org.example.agrotrack.iam.domain.model.entities.Role;
import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User extends AbstractDomainAggregateRoot<User> {

    private final String id;
    private String email;
    private String password;
    private final Set<Role> roles;

    private User(String id, String email, String password, Set<Role> roles) {
        this.id = id;
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.password = Objects.requireNonNull(password, "password must not be null");
        this.roles = roles != null ? roles : new HashSet<>();
    }

    public static User create(String email, String encodedPassword, List<Role> roles) {
        var user = new User(null, email, encodedPassword, new HashSet<>());
        user.addRoles(roles);
        return user;
    }

    public static User restore(String id, String email, String password, Set<Role> roles) {
        return new User(id, email, password, roles);
    }

    public void updateCredentials(String email, String encodedPassword) {
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.password = Objects.requireNonNull(encodedPassword, "encodedPassword must not be null");
    }

    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public User addRoles(List<Role> roles) {
        this.roles.addAll(Role.validateRoleSet(roles));
        return this;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
