package org.example.agrotrack.dashboard.domain.repositories;

import org.example.agrotrack.dashboard.domain.model.aggregates.LossSummary;

import java.util.List;
import java.util.Optional;

public interface LossSummaryRepository {

    Optional<LossSummary> findById(String id);

    List<LossSummary> findAll();

    LossSummary save(LossSummary summary);
}
