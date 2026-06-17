package org.example.agrotrack.dashboard.infrastructure.persistence.jpa.entities;

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
@Table(name = "loss_summaries")
public class LossSummaryEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "plot_id", nullable = false, length = 36)
    private String plotId;

    @Column(name = "loss_percentage", nullable = false)
    private double lossPercentage;

    @Column(nullable = false, length = 200)
    private String cause;

    @Column(nullable = false, length = 20)
    private String season;

    @Column(name = "calculated_at")
    private Instant calculatedAt;
}
