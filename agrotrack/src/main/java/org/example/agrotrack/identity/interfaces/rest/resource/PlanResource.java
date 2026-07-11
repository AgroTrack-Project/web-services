package org.example.agrotrack.identity.interfaces.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlanResource(
        Long id,
        @JsonProperty("plan_type") String planType,
        double price,
        @JsonProperty("max_plots") int maxPlots,
        @JsonProperty("is_dashboard_enabled") boolean isDashboardEnabled,
        @JsonProperty("is_export_enabled") boolean isExportEnabled,
        @JsonProperty("has_priority_support") boolean hasPrioritySupport
) {}
