package org.example.agrotrack.farming.application.internal.services;

import org.example.agrotrack.soilmonitoring.interfaces.acl.IrrigationRecommendationSummary;
import org.example.agrotrack.soilmonitoring.interfaces.acl.SoilRecordSummary;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Derives estimated harvest metrics (yield, loss, water usage) from a plot's recorded soil
 * readings and irrigation recommendation history. There is no direct measurement of these
 * values at harvest time, so the calculation is a heuristic scoring model rather than a
 * precise agronomic formula: soil condition, irrigation responsiveness, and cycle duration
 * each contribute a multiplicative factor against a fixed baseline yield.
 */
public final class HarvestMetricsCalculator {

    private static final double BASE_YIELD_PER_HECTARE = 4.0;
    private static final double MIN_YIELD_PER_HECTARE = 1.0;
    private static final double MAX_YIELD_PER_HECTARE = 9.0;

    private HarvestMetricsCalculator() {
    }

    public record Metrics(
            double yieldPerHectare,
            double lossPercentage,
            String lossCause,
            double totalLiters
    ) {
    }

    /**
     * Computes the full metrics set for one harvest. All inputs are the historical soil and
     * irrigation records for the crop's plot across its growing cycle, not just at harvest.
     */
    public static Metrics calculate(
            LocalDate sowingDate,
            LocalDate harvestDate,
            List<SoilRecordSummary> soilRecords,
            List<IrrigationRecommendationSummary> recommendations,
            double sizeHectares
    ) {
        double soilFactor = soilFactor(soilRecords);
        double irrigationFactor = irrigationFactor(recommendations);
        double durationFactor = durationFactor(sowingDate, harvestDate);

        double yieldPerHectare = clamp(
                BASE_YIELD_PER_HECTARE * soilFactor * irrigationFactor * durationFactor,
                MIN_YIELD_PER_HECTARE,
                MAX_YIELD_PER_HECTARE
        );

        double lossPercentage = clamp((1 - soilFactor) * 40 + (1 - irrigationFactor) * 40, 0, 60);
        String lossCause = lossCause(soilRecords, recommendations);
        double totalLiters = waterLiters(recommendations, sizeHectares);

        return new Metrics(round2(yieldPerHectare), round2(lossPercentage), lossCause, round2(totalLiters));
    }

    // Scores soil health as observed during the cycle. With no readings at all, a neutral-ish
    // 0.8 is assumed rather than penalizing plots with sparse monitoring; otherwise the factor
    // scales linearly from 0.6 (no optimal readings) to 1.0 (all readings optimal).
    private static double soilFactor(List<SoilRecordSummary> records) {
        if (records.isEmpty()) {
            return 0.8;
        }
        long optimal = records.stream().filter(r -> "OPTIMAL".equals(r.status())).count();
        return 0.6 + 0.4 * ((double) optimal / records.size());
    }

    // Only HIGH/CRITICAL urgency recommendations matter here — low-urgency advice being ignored
    // shouldn't drag the score down. No urgent recommendations at all means irrigation was never
    // a limiting factor, hence the 1.0 (no penalty) short-circuit.
    private static double irrigationFactor(List<IrrigationRecommendationSummary> recommendations) {
        List<IrrigationRecommendationSummary> important = recommendations.stream()
                .filter(r -> "HIGH".equals(r.urgency()) || "CRITICAL".equals(r.urgency()))
                .toList();
        if (important.isEmpty()) {
            return 1.0;
        }
        long confirmed = important.stream().filter(r -> "CONFIRMED".equals(r.status())).count();
        return 0.6 + 0.4 * ((double) confirmed / important.size());
    }

    // Rewards longer growing cycles up to a 120-day cap, on the assumption that a crop harvested
    // too soon (or with a harvest date not after sowing) underperforms. Clamped to [0.7, 1.2] so
    // this factor alone can't push yield outside the calculate() bounds by more than the other
    // two factors already allow.
    private static double durationFactor(LocalDate sowingDate, LocalDate harvestDate) {
        long days = ChronoUnit.DAYS.between(sowingDate, harvestDate);
        if (days <= 0) {
            return 0.7;
        }
        double factor = 0.8 + Math.min(days, 120) / 120.0 * 0.4;
        return clamp(factor, 0.7, 1.2);
    }

    // Picks a single human-readable cause for reporting, in priority order: unaddressed urgent
    // irrigation advice first (it's the most actionable cause), then dry vs. wet soil readings
    // compared head-to-head, falling back to a generic weather-related cause when neither signal
    // is present. User-facing strings are in Spanish to match the rest of the dashboard copy.
    private static String lossCause(List<SoilRecordSummary> soilRecords, List<IrrigationRecommendationSummary> recommendations) {
        long dryCount = soilRecords.stream().filter(r -> "DRY".equals(r.status())).count();
        long wetCount = soilRecords.stream().filter(r -> "WET".equals(r.status())).count();
        long rejectedImportant = recommendations.stream()
                .filter(r -> ("HIGH".equals(r.urgency()) || "CRITICAL".equals(r.urgency())) && "REJECTED".equals(r.status()))
                .count();

        if (rejectedImportant > 0 && rejectedImportant >= dryCount) {
            return "Riego insuficiente";
        }
        if (dryCount >= wetCount && dryCount > 0) {
            return "Estrés hídrico";
        }
        if (wetCount > 0) {
            return "Exceso de humedad";
        }
        return "Condiciones climáticas";
    }

    // Only CONFIRMED recommendations are assumed to have actually been irrigated; rejected or
    // pending ones don't contribute water usage. sizeHectares is floored at 0.1 to avoid a
    // near-zero or missing plot size collapsing total consumption to zero.
    private static double waterLiters(List<IrrigationRecommendationSummary> recommendations, double sizeHectares) {
        double total = recommendations.stream()
                .filter(r -> "CONFIRMED".equals(r.status()))
                .mapToDouble(r -> litersFor(r.urgency()))
                .sum();
        return total * Math.max(sizeHectares, 0.1);
    }

    private static double litersFor(String urgency) {
        return switch (urgency) {
            case "CRITICAL" -> 3000;
            case "HIGH" -> 2000;
            case "MEDIUM" -> 1000;
            default -> 500;
        };
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
