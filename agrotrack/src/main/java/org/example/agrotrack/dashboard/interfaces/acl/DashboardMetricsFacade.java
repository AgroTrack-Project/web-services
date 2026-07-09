package org.example.agrotrack.dashboard.interfaces.acl;

public interface DashboardMetricsFacade {

    void recordYield(String plotId, double yieldPerHectare, String season);

    void recordLoss(String plotId, double lossPercentage, String cause, String season);

    void recordWaterConsumption(String plotId, double totalLiters, String season);
}
