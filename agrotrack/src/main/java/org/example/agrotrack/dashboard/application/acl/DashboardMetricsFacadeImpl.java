package org.example.agrotrack.dashboard.application.acl;

import org.example.agrotrack.dashboard.application.commandservices.LossSummaryCommandService;
import org.example.agrotrack.dashboard.application.commandservices.WaterConsumptionCommandService;
import org.example.agrotrack.dashboard.application.commandservices.YieldSummaryCommandService;
import org.example.agrotrack.dashboard.domain.model.commands.CreateLossSummaryCommand;
import org.example.agrotrack.dashboard.domain.model.commands.CreateWaterConsumptionCommand;
import org.example.agrotrack.dashboard.domain.model.commands.CreateYieldSummaryCommand;
import org.example.agrotrack.dashboard.interfaces.acl.DashboardMetricsFacade;
import org.springframework.stereotype.Service;

@Service
public class DashboardMetricsFacadeImpl implements DashboardMetricsFacade {

    private final YieldSummaryCommandService yieldSummaryCommandService;
    private final LossSummaryCommandService lossSummaryCommandService;
    private final WaterConsumptionCommandService waterConsumptionCommandService;

    public DashboardMetricsFacadeImpl(
            YieldSummaryCommandService yieldSummaryCommandService,
            LossSummaryCommandService lossSummaryCommandService,
            WaterConsumptionCommandService waterConsumptionCommandService
    ) {
        this.yieldSummaryCommandService = yieldSummaryCommandService;
        this.lossSummaryCommandService = lossSummaryCommandService;
        this.waterConsumptionCommandService = waterConsumptionCommandService;
    }

    @Override
    public void recordYield(String plotId, double yieldPerHectare, String season) {
        yieldSummaryCommandService.handle(new CreateYieldSummaryCommand(plotId, yieldPerHectare, season));
    }

    @Override
    public void recordLoss(String plotId, double lossPercentage, String cause, String season) {
        lossSummaryCommandService.handle(new CreateLossSummaryCommand(plotId, lossPercentage, cause, season));
    }

    @Override
    public void recordWaterConsumption(String plotId, double totalLiters, String season) {
        waterConsumptionCommandService.handle(new CreateWaterConsumptionCommand(plotId, totalLiters, season));
    }
}
