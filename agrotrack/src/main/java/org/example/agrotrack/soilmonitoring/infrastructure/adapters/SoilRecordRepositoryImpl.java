package org.example.agrotrack.soilmonitoring.infrastructure.adapters;

import org.example.agrotrack.soilmonitoring.domain.model.aggregates.SoilRecord;
import org.example.agrotrack.soilmonitoring.domain.repositories.SoilRecordRepository;
import org.example.agrotrack.soilmonitoring.infrastructure.assemblers.SoilRecordPersistenceAssembler;
import org.example.agrotrack.soilmonitoring.infrastructure.repositories.SoilRecordPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SoilRecordRepositoryImpl implements SoilRecordRepository {

    private final SoilRecordPersistenceRepository persistenceRepository;

    public SoilRecordRepositoryImpl(SoilRecordPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<SoilRecord> findById(String id) {
        return persistenceRepository.findById(id)
                .map(SoilRecordPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<SoilRecord> findAll() {
        return persistenceRepository.findAll().stream()
                .map(SoilRecordPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<SoilRecord> findByPlotIdOrderByRecordedAtDesc(String plotId) {
        return persistenceRepository.findByPlotIdOrderByRecordedAtDesc(plotId).stream()
                .map(SoilRecordPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public SoilRecord save(SoilRecord soilRecord) {
        var saved = persistenceRepository.save(
                SoilRecordPersistenceAssembler.toPersistenceFromDomain(soilRecord)
        );

        return SoilRecordPersistenceAssembler.toDomainFromPersistence(saved);
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