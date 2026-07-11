package org.example.agrotrack.farming.domain.repositories;

import org.example.agrotrack.farming.domain.model.aggregates.Plot;

import java.util.List;
import java.util.Optional;

/**
 * Domain-level port for plot persistence. Implemented by an infrastructure adapter that
 * delegates to the JPA repository and translates between domain aggregates and persistence
 * entities, keeping the domain layer free of JPA concerns. Also consumed outside this bounded
 * context (e.g. harvest metrics generation) to resolve a plot's size for yield calculations.
 */
public interface PlotRepository {

    Optional<Plot> findById(String id);

    List<Plot> findAll();

    List<Plot> findByUserId(String userId);

    /**
     * Inserts or updates depending on whether {@code plot.getId()} is {@code null}.
     */
    Plot save(Plot plot);

    /**
     * Hard-deletes the plot; there is no soft-delete path.
     */
    void deleteById(String id);
}
