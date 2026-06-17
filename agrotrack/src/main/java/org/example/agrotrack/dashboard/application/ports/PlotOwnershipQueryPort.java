package org.example.agrotrack.dashboard.application.ports;

import java.util.List;
import java.util.Optional;

public interface PlotOwnershipQueryPort {

    List<String> findActivePlotIdsByUserId(String userId);

    boolean plotBelongsToUser(String plotId, String userId);

    Optional<PlotSummary> findPlotById(String plotId);
}
