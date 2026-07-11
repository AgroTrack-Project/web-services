package org.example.agrotrack.farming.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.agrotrack.farming.domain.model.valueobjects.CropStatus;
import org.example.agrotrack.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;

import java.time.LocalDate;

/**
 * JPA mapping for the {@code crops} table. Deliberately kept separate from the {@code Crop}
 * domain aggregate (no business methods, no invariants) so persistence concerns don't leak
 * into the domain model; conversion between the two happens in {@code CropPersistenceAssembler}.
 * The id column and auditing fields are inherited from {@link AuditableAbstractPersistenceEntity}.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "crops")
public class CropPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "plot_id", nullable = false, length = 36)
    private String plotId;

    @Column(nullable = false, length = 100)
    private String type;

    @Column(name = "sowing_date", nullable = false)
    private LocalDate sowingDate;

    @Column(name = "harvest_date")
    private LocalDate harvestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CropStatus status;
}
