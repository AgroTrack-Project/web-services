package org.example.agrotrack.identity.domain.model.aggregates;

import org.example.agrotrack.identity.domain.model.valueobjects.PlanType;
import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.util.Objects;

public class Plan extends AbstractDomainAggregateRoot<Plan> {

    private final Long id;
    private final PlanType planType;
    private final double price;
    private final int maxPlots;
    private final boolean isDashboardEnabled;
    private final boolean isExportEnabled;
    private final boolean hasPrioritySupport;

    private Plan(Long id, PlanType planType, double price, int maxPlots,
                 boolean isDashboardEnabled, boolean isExportEnabled, boolean hasPrioritySupport) {
        this.id = id;
        this.planType = Objects.requireNonNull(planType, "planType must not be null");
        this.price = price;
        this.maxPlots = maxPlots;
        this.isDashboardEnabled = isDashboardEnabled;
        this.isExportEnabled = isExportEnabled;
        this.hasPrioritySupport = hasPrioritySupport;
    }

    public static Plan create(PlanType planType, double price, int maxPlots,
                              boolean isDashboardEnabled, boolean isExportEnabled, boolean hasPrioritySupport) {
        return new Plan(null, planType, price, maxPlots, isDashboardEnabled, isExportEnabled, hasPrioritySupport);
    }

    public static Plan restore(Long id, PlanType planType, double price, int maxPlots,
                               boolean isDashboardEnabled, boolean isExportEnabled, boolean hasPrioritySupport) {
        return new Plan(id, planType, price, maxPlots, isDashboardEnabled, isExportEnabled, hasPrioritySupport);
    }

    public Long getId() { return id; }
    public PlanType getPlanType() { return planType; }
    public double getPrice() { return price; }
    public int getMaxPlots() { return maxPlots; }
    public boolean isDashboardEnabled() { return isDashboardEnabled; }
    public boolean isExportEnabled() { return isExportEnabled; }
    public boolean hasPrioritySupport() { return hasPrioritySupport; }
}
