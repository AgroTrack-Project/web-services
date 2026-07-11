package org.example.agrotrack.iam.infrastructure.persistence.jpa.entities;

import org.example.agrotrack.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "IamUserPersistenceEntity")
@Table(name = "iam_users")
public class UserPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false, length = 120)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "iam_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RolePersistenceEntity> roles = new HashSet<>();
}
