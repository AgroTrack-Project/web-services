package org.example.agrotrack.soilmonitoring.application.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.soilmonitoring.domain.model.aggregates.SoilRecord;
import org.example.agrotrack.soilmonitoring.domain.model.queries.ListSoilRecordsQuery;

import java.util.List;

public interface SoilRecordQueryService {

    Result<List<SoilRecord>, ApplicationError> handle(ListSoilRecordsQuery query);
}