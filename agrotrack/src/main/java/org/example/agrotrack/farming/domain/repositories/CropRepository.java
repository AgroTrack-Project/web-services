package org.example.agrotrack.farming.domain.repositories;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;

import java.util.List;
import java.util.Optional;

public interface CropRepository {

    Optional<Crop> findById(Long id);

    List<Crop> findAll();

    List<Crop> findByPlotId(Long plotId);

    Crop save(Crop crop);

    void deleteById(Long id);
}
