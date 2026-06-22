package org.example.agrotrack.dashboard.infrastructure.adapters;

import org.example.agrotrack.dashboard.domain.model.aggregates.LossSummary;
import org.example.agrotrack.dashboard.domain.repositories.LossSummaryRepository;
import org.example.agrotrack.dashboard.infrastructure.assemblers.LossSummaryPersistenceAssembler;
import org.example.agrotrack.dashboard.infrastructure.repositories.LossSummaryPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LossSummaryRepositoryImpl implements LossSummaryRepository {

    private final LossSummaryPersistenceRepository persistenceRepository;

    public LossSummaryRepositoryImpl(LossSummaryPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<LossSummary> findById(String id) {
        return persistenceRepository.findById(id).map(LossSummaryPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<LossSummary> findAll() {
        return persistenceRepository.findAll().stream()
                .map(LossSummaryPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }
}
