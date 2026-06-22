package org.example.agrotrack.dashboard.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.dashboard.infrastructure.entities.LossSummaryPersistenceEntity;
import org.example.agrotrack.dashboard.infrastructure.entities.WaterConsumptionPersistenceEntity;
import org.example.agrotrack.dashboard.infrastructure.entities.YieldSummaryPersistenceEntity;
import org.example.agrotrack.dashboard.infrastructure.repositories.LossSummaryPersistenceRepository;
import org.example.agrotrack.dashboard.infrastructure.repositories.WaterConsumptionPersistenceRepository;
import org.example.agrotrack.dashboard.infrastructure.repositories.YieldSummaryPersistenceRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Seeds dashboard metrics aligned with MockAPI plot ids (GET /plots).
 * User 1 plots: 27, 28, 29 — user 2 plots: 3, 4 — user 3 plots: 25, 26, 30, 31.
 */
@Component
@Profile("dev")
@RequiredArgsConstructor
public class DashboardDataSeeder implements ApplicationRunner {

    private static final String SEASON = "2025-2026";

    private final YieldSummaryPersistenceRepository yieldSummaryRepository;
    private final LossSummaryPersistenceRepository lossSummaryRepository;
    private final WaterConsumptionPersistenceRepository waterConsumptionRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (yieldSummaryRepository.count() > 0) {
            return;
        }

        seedYieldSummaries();
        seedLossSummaries();
        seedWaterConsumptions();
    }

    private void seedYieldSummaries() {
        yieldSummaryRepository.save(seedYield("27", 4.25));
        yieldSummaryRepository.save(seedYield("28", 3.80));
        yieldSummaryRepository.save(seedYield("29", 5.10));
        yieldSummaryRepository.save(seedYield("3", 6.20));
        yieldSummaryRepository.save(seedYield("4", 5.75));
    }

    private void seedLossSummaries() {
        lossSummaryRepository.save(loss("27", 8.5, "Plagas de insectos"));
        lossSummaryRepository.save(loss("28", 12.0, "Helada tardía"));
        lossSummaryRepository.save(loss("29", 5.2, "Exceso de riego"));
        lossSummaryRepository.save(loss("3", 3.8, "Viento fuerte"));
    }

    private void seedWaterConsumptions() {
        waterConsumptionRepository.save(water("27", 12_500));
        waterConsumptionRepository.save(water("28", 9_800));
        waterConsumptionRepository.save(water("29", 14_200));
        waterConsumptionRepository.save(water("3", 18_400));
        waterConsumptionRepository.save(water("4", 11_600));
    }

    private YieldSummaryPersistenceEntity seedYield(String plotId, double yieldPerHectare) {
        YieldSummaryPersistenceEntity entity = new YieldSummaryPersistenceEntity();
        entity.setPlotId(plotId);
        entity.setYieldPerHectare(yieldPerHectare);
        entity.setSeason(SEASON);
        entity.setCalculatedAt(Instant.now().minusSeconds(3_600));
        return entity;
    }

    private LossSummaryPersistenceEntity loss(String plotId, double lossPercentage, String cause) {
        LossSummaryPersistenceEntity entity = new LossSummaryPersistenceEntity();
        entity.setPlotId(plotId);
        entity.setLossPercentage(lossPercentage);
        entity.setCause(cause);
        entity.setSeason(SEASON);
        entity.setCalculatedAt(Instant.now().minusSeconds(7_200));
        return entity;
    }

    private WaterConsumptionPersistenceEntity water(String plotId, double totalLiters) {
        WaterConsumptionPersistenceEntity entity = new WaterConsumptionPersistenceEntity();
        entity.setPlotId(plotId);
        entity.setTotalLiters(totalLiters);
        entity.setSeason(SEASON);
        entity.setCalculatedAt(Instant.now().minusSeconds(5_400));
        return entity;
    }
}
