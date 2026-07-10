package org.example.agrotrack.farming.application.queryservices;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.domain.model.queries.ListCropsQuery;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

import java.util.List;

/**
 * Application-layer port for crop reads. Kept separate from {@link
 * org.example.agrotrack.farming.application.commandservices.CropCommandService} to follow
 * the project's CQRS split between reads and writes.
 */
public interface CropQueryService {

    Result<List<Crop>, ApplicationError> handle(ListCropsQuery query);
}
