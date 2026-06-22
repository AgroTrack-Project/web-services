package org.example.agrotrack.soilmonitoring.domain.repositories;

import org.example.agrotrack.soilmonitoring.domain.model.aggregates.SoilRecord;

import java.util.List;
import java.util.Optional;

public interface SoilRecordRepository {

    Optional<SoilRecord> findById(String id);

    List<SoilRecord> findAll();

    List<SoilRecord> findByPlotIdOrderByRecordedAtDesc(String plotId);

    SoilRecord save(SoilRecord soilRecord);

    void deleteById(String id);

    void deleteByPlotId(String plotId);
}