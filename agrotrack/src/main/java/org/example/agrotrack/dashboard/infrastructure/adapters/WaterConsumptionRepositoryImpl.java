package org.example.agrotrack.dashboard.infrastructure.adapters;

import org.example.agrotrack.dashboard.domain.model.aggregates.WaterConsumption;
import org.example.agrotrack.dashboard.domain.repositories.WaterConsumptionRepository;
import org.example.agrotrack.dashboard.infrastructure.assemblers.WaterConsumptionPersistenceAssembler;
import org.example.agrotrack.dashboard.infrastructure.repositories.WaterConsumptionPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WaterConsumptionRepositoryImpl implements WaterConsumptionRepository {

    private final WaterConsumptionPersistenceRepository persistenceRepository;

    public WaterConsumptionRepositoryImpl(WaterConsumptionPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<WaterConsumption> findById(String id) {
        return persistenceRepository.findById(id).map(WaterConsumptionPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<WaterConsumption> findAll() {
        return persistenceRepository.findAll().stream()
                .map(WaterConsumptionPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }
}
