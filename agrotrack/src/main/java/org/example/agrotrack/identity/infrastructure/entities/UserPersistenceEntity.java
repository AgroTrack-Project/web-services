package org.example.agrotrack.identity.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.agrotrack.identity.domain.model.valueobjects.PlanType;
import org.example.agrotrack.identity.domain.model.valueobjects.UserType;
import org.example.agrotrack.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "iam_user_id", nullable = false, unique = true, length = 36)
    private String iamUserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, length = 30)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type", nullable = false, length = 20)
    private PlanType planType;

    @Column(name = "company_name", length = 200)
    private String companyName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
