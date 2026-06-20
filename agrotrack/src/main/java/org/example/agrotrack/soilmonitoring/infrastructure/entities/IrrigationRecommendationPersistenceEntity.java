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
@Table(name = "irrigation_recommendations")
public class IrrigationRecommendationPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "plot_id", nullable = false, length = 36)
    private String plotId;

    @Column(name = "soil_record_id", nullable = false, length = 36)
    private String soilRecordId;

    @Column(nullable = false, length = 255)
    private String message;

    @Column(nullable = false, length = 20)
    private String urgency;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "generated_at", nullable = false)
    private Instant generatedAt;

    @Column(name = "responded_at")
    private Instant respondedAt;
}