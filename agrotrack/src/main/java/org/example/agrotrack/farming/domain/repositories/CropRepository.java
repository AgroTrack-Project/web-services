package org.example.agrotrack.farming.domain.repositories;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;

import java.util.List;
import java.util.Optional;

/**
 * Domain-level port for crop persistence. Implemented by an infrastructure adapter that
 * delegates to the JPA repository and translates between domain aggregates and persistence
 * entities, keeping the domain layer free of JPA concerns.
 */
public interface CropRepository {

    Optional<Crop> findById(String id);

    List<Crop> findAll();

    List<Crop> findByPlotId(String plotId);

    /**
     * Inserts or updates depending on whether {@code crop.getId()} is {@code null}.
     */
    Crop save(Crop crop);

    /**
     * Hard-deletes the crop; there is no soft-delete path.
     */
    void deleteById(String id);
}
