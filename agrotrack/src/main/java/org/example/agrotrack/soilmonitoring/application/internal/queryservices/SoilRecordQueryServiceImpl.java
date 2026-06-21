package org.example.agrotrack.soilmonitoring.application.internal.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.soilmonitoring.application.queryservices.SoilRecordQueryService;
import org.example.agrotrack.soilmonitoring.domain.model.aggregates.SoilRecord;
import org.example.agrotrack.soilmonitoring.domain.model.queries.ListSoilRecordsQuery;
import org.example.agrotrack.soilmonitoring.domain.repositories.SoilRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class SoilRecordQueryServiceImpl implements SoilRecordQueryService {

    private final SoilRecordRepository repository;

    public SoilRecordQueryServiceImpl(SoilRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<List<SoilRecord>, ApplicationError> handle(ListSoilRecordsQuery query) {
        String plotId = query.plotId();

        if (plotId != null && !plotId.isBlank()) {
            return Result.success(repository.findByPlotIdOrderByRecordedAtDesc(plotId.trim()));
        }

        return Result.success(
                repository.findAll().stream()
                        .sorted(Comparator.comparing(SoilRecord::getRecordedAt).reversed())
                        .toList()
        );
    }
}