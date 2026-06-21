package org.example.agrotrack.farming.infrastructure.adapters;

import org.example.agrotrack.farming.domain.model.aggregates.Plot;
import org.example.agrotrack.farming.domain.repositories.PlotRepository;
import org.example.agrotrack.farming.infrastructure.assemblers.PlotPersistenceAssembler;
import org.example.agrotrack.farming.infrastructure.entities.PlotPersistenceEntity;
import org.example.agrotrack.farming.infrastructure.repositories.PlotPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlotRepositoryImpl implements PlotRepository {

    private final PlotPersistenceRepository persistenceRepository;

    public PlotRepositoryImpl(PlotPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<Plot> findById(Long id) {
        return persistenceRepository.findById(id)
                .map(PlotPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Plot> findAll() {
        return persistenceRepository.findAll().stream()
                .map(PlotPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<Plot> findByUserId(String userId) {
        return persistenceRepository.findByUserId(userId).stream()
                .map(PlotPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Plot save(Plot plot) {
        PlotPersistenceEntity entity;

        if (plot.getId() == null) {
            entity = PlotPersistenceAssembler.toPersistenceFromDomain(plot);
        } else {
            entity = persistenceRepository.findById(plot.getId())
                    .orElseGet(PlotPersistenceEntity::new);

            PlotPersistenceAssembler.copyToPersistenceFromDomain(plot, entity);
        }

        var saved = persistenceRepository.save(entity);

        return PlotPersistenceAssembler.toDomainFromPersistence(saved);
    }
}
