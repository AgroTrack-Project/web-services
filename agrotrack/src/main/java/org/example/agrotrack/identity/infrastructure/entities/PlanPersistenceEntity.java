package org.example.agrotrack.identity.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.agrotrack.identity.domain.model.valueobjects.PlanType;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plans")
public class PlanPersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type", nullable = false, unique = true, length = 20)
    private PlanType planType;

    @Column(nullable = false)
    private double price;

    @Column(name = "max_plots", nullable = false)
    private int maxPlots;

    @Column(name = "is_dashboard_enabled", nullable = false)
    private boolean isDashboardEnabled;

    @Column(name = "is_export_enabled", nullable = false)
    private boolean isExportEnabled;

    @Column(name = "has_priority_support", nullable = false)
    private boolean hasPrioritySupport;
}
