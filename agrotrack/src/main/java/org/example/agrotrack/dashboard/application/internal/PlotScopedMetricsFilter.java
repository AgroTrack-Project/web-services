package org.example.agrotrack.dashboard.application.internal;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.dashboard.application.ports.PlotOwnershipQueryPort;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PlotScopedMetricsFilter {

    private final PlotOwnershipQueryPort plotOwnershipQueryPort;

    public <T> List<T> applyUserScope(String userId, List<T> metrics, Function<T, String> plotIdAccessor) {
        if (userId == null || userId.isBlank()) {
            return metrics;
        }
        Set<String> allowedPlotIds = new HashSet<>(
                plotOwnershipQueryPort.findActivePlotIdsByUserId(userId.trim())
        );
        return metrics.stream()
                .filter(metric -> allowedPlotIds.contains(plotIdAccessor.apply(metric)))
                .toList();
    }
}
