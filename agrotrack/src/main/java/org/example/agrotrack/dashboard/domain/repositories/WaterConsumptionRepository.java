package org.example.agrotrack.dashboard.domain.repositories;

import org.example.agrotrack.dashboard.domain.model.aggregates.WaterConsumption;

import java.util.List;
import java.util.Optional;

public interface WaterConsumptionRepository {

    Optional<WaterConsumption> findById(String id);

    List<WaterConsumption> findAll();

    WaterConsumption save(WaterConsumption consumption);
}
