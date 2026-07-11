package org.example.agrotrack.farming.infrastructure.adapters;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.domain.repositories.CropRepository;
import org.example.agrotrack.farming.infrastructure.assemblers.CropPersistenceAssembler;
import org.example.agrotrack.farming.infrastructure.entities.CropPersistenceEntity;
import org.example.agrotrack.farming.infrastructure.repositories.CropPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adapter implementing the {@link CropRepository} domain port on top of Spring Data JPA,
 * translating between {@code Crop} aggregates and {@code CropPersistenceEntity} rows via
 * {@link CropPersistenceAssembler}.
 */
@Repository
public class CropRepositoryImpl implements CropRepository {

    private final CropPersistenceRepository persistenceRepository;

    public CropRepositoryImpl(CropPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<Crop> findById(String id) {
        return persistenceRepository.findById(id)
                .map(CropPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Crop> findAll() {
        return persistenceRepository.findAll().stream()
                .map(CropPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<Crop> findByPlotId(String plotId) {
        return persistenceRepository.findByPlotId(plotId).stream()
                .map(CropPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Crop save(Crop crop) {
        CropPersistenceEntity entity;

        // A null id means this is a new crop, so a fresh entity is built. Otherwise the existing
        // row is loaded first and updated in place via copyToPersistenceFromDomain, rather than
        // building a brand-new entity, so JPA/Hibernate treats this as an update rather than an
        // insert-after-delete and preserves the inherited auditing metadata.
        if (crop.getId() == null) {
            entity = CropPersistenceAssembler.toPersistenceFromDomain(crop);
        } else {
            entity = persistenceRepository.findById(crop.getId())
                    .orElseGet(CropPersistenceEntity::new);

            CropPersistenceAssembler.copyToPersistenceFromDomain(crop, entity);
        }

        var saved = persistenceRepository.save(entity);

        return CropPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public void deleteById(String id) {
        persistenceRepository.deleteById(id);
    }
}
