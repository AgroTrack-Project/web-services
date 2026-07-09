package org.example.agrotrack.farming.application.internal.services;

import org.example.agrotrack.dashboard.interfaces.acl.DashboardMetricsFacade;
import org.example.agrotrack.farming.domain.repositories.PlotRepository;
import org.example.agrotrack.soilmonitoring.interfaces.acl.SoilMonitoringContextFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HarvestMetricsGenerator {

    private static final double LOSS_REPORT_THRESHOLD = 2.0;

    private final PlotRepository plotRepository;
    private final SoilMonitoringContextFacade soilMonitoringContextFacade;
    private final DashboardMetricsFacade dashboardMetricsFacade;

    public HarvestMetricsGenerator(
            PlotRepository plotRepository,
            SoilMonitoringContextFacade soilMonitoringContextFacade,
            DashboardMetricsFacade dashboardMetricsFacade
    ) {
        this.plotRepository = plotRepository;
        this.soilMonitoringContextFacade = soilMonitoringContextFacade;
        this.dashboardMetricsFacade = dashboardMetricsFacade;
    }

    public void generateForHarvest(String plotId, LocalDate sowingDate, LocalDate harvestDate) {
        var plotOptional = plotRepository.findById(plotId);
        if (plotOptional.isEmpty()) {
            return;
        }
        Double plotSize = plotOptional.get().getSizeHectares();
        double sizeHectares = plotSize != null ? plotSize : 1.0;

        var soilRecords = soilMonitoringContextFacade.getSoilRecordsForPlot(plotId);
        var recommendations = soilMonitoringContextFacade.getIrrigationRecommendationsForPlot(plotId);

        var metrics = HarvestMetricsCalculator.calculate(sowingDate, harvestDate, soilRecords, recommendations, sizeHectares);
        String season = seasonLabel(sowingDate, harvestDate);

        dashboardMetricsFacade.recordYield(plotId, metrics.yieldPerHectare(), season);

        if (metrics.lossPercentage() > LOSS_REPORT_THRESHOLD) {
            dashboardMetricsFacade.recordLoss(plotId, metrics.lossPercentage(), metrics.lossCause(), season);
        }

        if (metrics.totalLiters() > 0) {
            dashboardMetricsFacade.recordWaterConsumption(plotId, metrics.totalLiters(), season);
        }
    }

    private static String seasonLabel(LocalDate sowingDate, LocalDate harvestDate) {
        int sowingYear = sowingDate.getYear();
        int harvestYear = harvestDate.getYear();
        if (sowingYear == harvestYear) {
            return sowingYear + "-" + (sowingYear + 1);
        }
        return sowingYear + "-" + harvestYear;
    }
}
