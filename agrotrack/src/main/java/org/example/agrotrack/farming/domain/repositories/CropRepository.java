package org.example.agrotrack.farming.domain.repositories;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;

import java.util.List;
import java.util.Optional;

public interface CropRepository {

    Optional<Crop> findById(String id);

    List<Crop> findAll();

    List<Crop> findByPlotId(String plotId);

    Crop save(Crop crop);

    void deleteById(String id);
}
