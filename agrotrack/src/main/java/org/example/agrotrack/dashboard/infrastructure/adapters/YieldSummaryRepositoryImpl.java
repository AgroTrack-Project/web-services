package org.example.agrotrack.dashboard.infrastructure.adapters;

import org.example.agrotrack.dashboard.domain.model.aggregates.YieldSummary;
import org.example.agrotrack.dashboard.domain.repositories.YieldSummaryRepository;
import org.example.agrotrack.dashboard.infrastructure.assemblers.YieldSummaryPersistenceAssembler;
import org.example.agrotrack.dashboard.infrastructure.repositories.YieldSummaryPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class YieldSummaryRepositoryImpl implements YieldSummaryRepository {

    private final YieldSummaryPersistenceRepository persistenceRepository;

    public YieldSummaryRepositoryImpl(YieldSummaryPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<YieldSummary> findById(String id) {
        return persistenceRepository.findById(id).map(YieldSummaryPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<YieldSummary> findAll() {
        return persistenceRepository.findAll().stream()
                .map(YieldSummaryPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }
}
