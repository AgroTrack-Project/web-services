package org.example.agrotrack.farming.domain.repositories;

import org.example.agrotrack.farming.domain.model.aggregates.Plot;

import java.util.List;
import java.util.Optional;

public interface PlotRepository {

    Optional<Plot> findById(String id);

    List<Plot> findAll();

    List<Plot> findByUserId(String userId);

    Plot save(Plot plot);

    void deleteById(String id);
}
