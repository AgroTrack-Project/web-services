package org.example.agrotrack.farming.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.agrotrack.farming.domain.model.valueobjects.PlotStatus;
import org.example.agrotrack.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;

import java.time.LocalDateTime;

/**
 * JPA mapping for the {@code plots} table. Deliberately kept separate from the {@code Plot}
 * domain aggregate (no business methods, no invariants) so persistence concerns don't leak
 * into the domain model; conversion between the two happens in {@code PlotPersistenceAssembler}.
 * {@code createdAt} is tracked as its own column (rather than relying solely on the inherited
 * auditing fields) since it is exposed directly on the domain aggregate and API resource.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plots")
public class PlotPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(name = "size_hectares", nullable = false)
    private Double sizeHectares;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PlotStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
