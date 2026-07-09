package org.example.agrotrack.dashboard.infrastructure.adapters;

import org.example.agrotrack.dashboard.application.ports.PlotOwnershipQueryPort;
import org.example.agrotrack.dashboard.application.ports.PlotSummary;
import org.example.agrotrack.farming.domain.model.aggregates.Plot;
import org.example.agrotrack.farming.domain.repositories.PlotRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@ConditionalOnProperty(name = "agrotrack.integration.farming.mode", havingValue = "local")
public class FarmingPlotOwnershipAdapter implements PlotOwnershipQueryPort {

    private final PlotRepository plotRepository;

    public FarmingPlotOwnershipAdapter(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }


    @Override
    public List<String> findActivePlotIdsByUserId(String userId) {
        if (userId == null || userId.isBlank()) {
            return List.of();
        }
        return plotRepository.findByUserId(userId.trim()).stream()
                .map(Plot::getId)
                .toList();
    }

    @Override
    public boolean plotBelongsToUser(String plotId, String userId) {
        if (plotId == null || plotId.isBlank() || userId == null || userId.isBlank()) {
            return false;
        }
        return findPlotById(plotId)
                .map(plot -> userId.trim().equals(plot.userId()))
                .orElse(false);
    }

    @Override
    public Optional<PlotSummary> findPlotById(String plotId) {
        if (plotId == null || plotId.isBlank()) {
            return Optional.empty();
        }
        return plotRepository.findById(plotId.trim())
                .map(FarmingPlotOwnershipAdapter::toPlotSummary);
    }

    private static PlotSummary toPlotSummary(Plot plot) {
        return new PlotSummary(
                plot.getId(),
                plot.getUserId(),
                plot.getName(),
                plot.getStatus().name()
        );
    }
}