package org.example.agrotrack.identity.infrastructure.adapters;

import org.example.agrotrack.identity.domain.model.aggregates.Plan;
import org.example.agrotrack.identity.domain.model.valueobjects.PlanType;
import org.example.agrotrack.identity.domain.repositories.PlanRepository;
import org.example.agrotrack.identity.infrastructure.assemblers.PlanPersistenceAssembler;
import org.example.agrotrack.identity.infrastructure.repositories.PlanPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlanRepositoryImpl implements PlanRepository {

    private final PlanPersistenceRepository persistenceRepository;

    public PlanRepositoryImpl(PlanPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<Plan> findById(Long id) {
        return persistenceRepository.findById(id).map(PlanPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<Plan> findByPlanType(PlanType planType) {
        return persistenceRepository.findByPlanType(planType).map(PlanPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Plan> findAll() {
        return persistenceRepository.findAll().stream()
                .map(PlanPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Plan save(Plan plan) {
        return PlanPersistenceAssembler.toDomainFromPersistence(
                persistenceRepository.save(PlanPersistenceAssembler.toPersistenceFromDomain(plan))
        );
    }

    @Override
    public long count() {
        return persistenceRepository.count();
    }
}
