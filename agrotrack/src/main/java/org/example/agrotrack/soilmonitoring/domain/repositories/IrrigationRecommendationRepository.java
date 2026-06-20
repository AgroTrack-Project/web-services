package org.example.agrotrack.soilmonitoring.domain.repositories;

import org.example.agrotrack.soilmonitoring.domain.model.aggregates.IrrigationRecommendation;

import java.util.List;
import java.util.Optional;

public interface IrrigationRecommendationRepository {

    Optional<IrrigationRecommendation> findById(String id);

    List<IrrigationRecommendation> findAll();

    List<IrrigationRecommendation> findByPlotIdOrderByGeneratedAtDesc(String plotId);

    IrrigationRecommendation save(IrrigationRecommendation recommendation);

    void deleteById(String id);

    void deleteByPlotId(String plotId);
}