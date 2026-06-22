package org.example.agrotrack.dashboard.infrastructure.adapters;

import lombok.extern.slf4j.Slf4j;
import org.example.agrotrack.dashboard.application.ports.PlotOwnershipQueryPort;
import org.example.agrotrack.dashboard.application.ports.PlotSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@ConditionalOnProperty(name = "agrotrack.integration.farming.mode", havingValue = "mockapi")
public class MockApiPlotOwnershipAdapter implements PlotOwnershipQueryPort {

    private static final String DELETED_STATUS = "DELETED";
    private static final long CACHE_TTL_SECONDS = 60;

    private final RestClient restClient;
    private final Object cacheLock = new Object();
    private volatile List<PlotSummary> cachedPlots = List.of();
    private volatile Instant cacheExpiresAt = Instant.EPOCH;

    public MockApiPlotOwnershipAdapter(
            @Value("${agrotrack.integration.farming.mockapi-url}") String mockApiBaseUrl
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(mockApiBaseUrl)
                .build();
    }

    @Override
    public List<String> findActivePlotIdsByUserId(String userId) {
        if (userId == null || userId.isBlank()) {
            return List.of();
        }
        String normalizedUserId = userId.trim();
        return loadActivePlots().stream()
                .filter(plot -> normalizedUserId.equals(String.valueOf(plot.userId())))
                .map(PlotSummary::id)
                .toList();
    }

    @Override
    public boolean plotBelongsToUser(String plotId, String userId) {
        if (plotId == null || plotId.isBlank() || userId == null || userId.isBlank()) {
            return false;
        }
        return findPlotById(plotId)
                .map(plot -> userId.trim().equals(String.valueOf(plot.userId())))
                .orElse(false);
    }

    @Override
    public Optional<PlotSummary> findPlotById(String plotId) {
        if (plotId == null || plotId.isBlank()) {
            return Optional.empty();
        }
        String normalizedPlotId = plotId.trim();
        return loadActivePlots().stream()
                .filter(plot -> normalizedPlotId.equals(String.valueOf(plot.id())))
                .findFirst();
    }

    private List<PlotSummary> loadActivePlots() {
        if (Instant.now().isBefore(cacheExpiresAt)) {
            return cachedPlots;
        }
        synchronized (cacheLock) {
            if (Instant.now().isBefore(cacheExpiresAt)) {
                return cachedPlots;
            }
            cachedPlots = fetchPlotsFromMockApi();
            cacheExpiresAt = Instant.now().plusSeconds(CACHE_TTL_SECONDS);
            return cachedPlots;
        }
    }

    private List<PlotSummary> fetchPlotsFromMockApi() {
        try {
            List<MockApiPlotResponse> response = restClient.get()
                    .uri("/plots")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            if (response == null) {
                return List.of();
            }
            return response.stream()
                    .filter(Objects::nonNull)
                    .filter(plot -> plot.id() != null && !plot.id().isBlank())
                    .filter(plot -> !DELETED_STATUS.equalsIgnoreCase(
                            plot.status() == null ? "" : plot.status().trim()
                    ))
                    .map(plot -> new PlotSummary(
                            plot.id(),
                            plot.userId(),
                            plot.name(),
                            plot.status()
                    ))
                    .toList();
        } catch (RestClientException ex) {
            log.warn("Could not load plots from MockAPI: {}", ex.getMessage());
            return List.of();
        }
    }
}
