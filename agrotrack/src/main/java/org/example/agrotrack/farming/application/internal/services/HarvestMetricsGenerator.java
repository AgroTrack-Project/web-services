package org.example.agrotrack.farming.application.internal.services;

import org.example.agrotrack.dashboard.interfaces.acl.DashboardMetricsFacade;
import org.example.agrotrack.farming.domain.repositories.PlotRepository;
import org.example.agrotrack.soilmonitoring.interfaces.acl.SoilMonitoringContextFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Orchestrates dashboard metric recording when a crop is harvested. Pulls soil and irrigation
 * history from the soilmonitoring bounded context through its ACL facade, feeds it into
 * {@link HarvestMetricsCalculator}, and pushes the results into the dashboard bounded context
 * through its own facade — this class is the integration point between three bounded contexts
 * and intentionally the only place in farming that talks to soilmonitoring/dashboard directly.
 */
@Service
public class HarvestMetricsGenerator {

    // Below this loss percentage, a loss event isn't worth surfacing on the dashboard.
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

    /**
     * Computes and records yield, loss, and water-consumption metrics for the plot a just-harvested
     * crop belongs to. Silently no-ops if the plot can no longer be found, since this runs as a
     * best-effort side effect of harvesting (see the caller in {@code CropCommandServiceImpl}).
     */
    public void generateForHarvest(String plotId, LocalDate sowingDate, LocalDate harvestDate) {
        var plotOptional = plotRepository.findById(plotId);
        if (plotOptional.isEmpty()) {
            return;
        }
        // Falls back to 1 hectare when size is missing so per-hectare yield stays computable
        // rather than dividing by (or multiplying by) zero/null downstream.
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

    // Produces a "YYYY-YYYY" season label for dashboard grouping. When sowing and harvest fall
    // in the same calendar year, the label still spans into the following year (e.g. "2026-2027")
    // to represent an agricultural season rather than a literal calendar year.
    private static String seasonLabel(LocalDate sowingDate, LocalDate harvestDate) {
        int sowingYear = sowingDate.getYear();
        int harvestYear = harvestDate.getYear();
        if (sowingYear == harvestYear) {
            return sowingYear + "-" + (sowingYear + 1);
        }
        return sowingYear + "-" + harvestYear;
    }
}
