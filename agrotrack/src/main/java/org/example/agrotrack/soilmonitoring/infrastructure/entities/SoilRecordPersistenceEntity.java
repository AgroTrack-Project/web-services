package org.example.agrotrack.soilmonitoring.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.agrotrack.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "soil_records")
public class SoilRecordPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "plot_id", nullable = false, length = 36)
    private String plotId;

    @Column(nullable = false)
    private Double humidity;

    @Column(nullable = false)
    private Double temperature;

    @Column(name = "recorded_at", nullable = false)
    private Instant recordedAt;
}