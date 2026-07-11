package org.example.agrotrack.soilmonitoring.infrastructure.adapters;

import org.example.agrotrack.soilmonitoring.domain.model.aggregates.IrrigationRecommendation;
import org.example.agrotrack.soilmonitoring.domain.repositories.IrrigationRecommendationRepository;
import org.example.agrotrack.soilmonitoring.infrastructure.assemblers.IrrigationRecommendationPersistenceAssembler;
import org.example.agrotrack.soilmonitoring.infrastructure.entities.IrrigationRecommendationPersistenceEntity;
import org.example.agrotrack.soilmonitoring.infrastructure.repositories.IrrigationRecommendationPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IrrigationRecommendationRepositoryImpl implements IrrigationRecommendationRepository {

    private final IrrigationRecommendationPersistenceRepository persistenceRepository;

    public IrrigationRecommendationRepositoryImpl(
            IrrigationRecommendationPersistenceRepository persistenceRepository
    ) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<IrrigationRecommendation> findById(String id) {
        return persistenceRepository.findById(id)
                .map(IrrigationRecommendationPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<IrrigationRecommendation> findAll() {
        return persistenceRepository.findAll().stream()
                .map(IrrigationRecommendationPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<IrrigationRecommendation> findByPlotIdOrderByGeneratedAtDesc(String plotId) {
        return persistenceRepository.findByPlotIdOrderByGeneratedAtDesc(plotId).stream()
                .map(IrrigationRecommendationPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public IrrigationRecommendation save(IrrigationRecommendation recommendation) {
        IrrigationRecommendationPersistenceEntity entity;

        if (recommendation.getId() == null || recommendation.getId().isBlank()) {
            entity = IrrigationRecommendationPersistenceAssembler.toPersistenceFromDomain(recommendation);
        } else {
            entity = persistenceRepository.findById(recommendation.getId())
                    .orElseGet(IrrigationRecommendationPersistenceEntity::new);

            IrrigationRecommendationPersistenceAssembler.copyToPersistenceFromDomain(recommendation, entity);
        }

        var saved = persistenceRepository.save(entity);

        return IrrigationRecommendationPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public void deleteById(String id) {
        persistenceRepository.deleteById(id);
    }

    @Override
    public void deleteByPlotId(String plotId) {
        persistenceRepository.deleteByPlotId(plotId);
    }
}