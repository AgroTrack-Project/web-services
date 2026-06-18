package org.example.agrotrack.dashboard.domain.repositories;

import org.example.agrotrack.dashboard.domain.model.aggregates.YieldSummary;

import java.util.List;
import java.util.Optional;

public interface YieldSummaryRepository {

    Optional<YieldSummary> findById(String id);

    List<YieldSummary> findAll();
}
