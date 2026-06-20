package org.example.agrotrack.identity.domain.model.aggregates;

import org.example.agrotrack.identity.domain.model.valueobjects.PlanType;
import org.example.agrotrack.identity.domain.model.valueobjects.UserType;
import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.time.Instant;
import java.util.Objects;

public class User extends AbstractDomainAggregateRoot<User> {

    private final String id;
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private PlanType planType;
    private String companyName;
    private final Instant createdAt;
    private Instant updatedAt;

    private User(String id, String name, String email, String password,
                 UserType userType, PlanType planType, String companyName,
                 Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.password = Objects.requireNonNull(password, "password must not be null");
        this.userType = Objects.requireNonNull(userType, "userType must not be null");
        this.planType = Objects.requireNonNull(planType, "planType must not be null");
        this.companyName = companyName;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static User create(String name, String email, String password,
                              UserType userType, PlanType planType, String companyName) {
        Instant now = Instant.now();
        return new User(null, name, email, password, userType, planType, companyName, now, now);
    }

    public static User restore(String id, String name, String email, String password,
                               UserType userType, PlanType planType, String companyName,
                               Instant createdAt, Instant updatedAt) {
        return new User(id, name, email, password, userType, planType, companyName, createdAt, updatedAt);
    }

    public void update(String name, String email, String password, PlanType planType, String companyName) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.password = Objects.requireNonNull(password, "password must not be null");
        this.planType = Objects.requireNonNull(planType, "planType must not be null");
        this.companyName = companyName;
        this.updatedAt = Instant.now();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public UserType getUserType() { return userType; }
    public PlanType getPlanType() { return planType; }
    public String getCompanyName() { return companyName; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
