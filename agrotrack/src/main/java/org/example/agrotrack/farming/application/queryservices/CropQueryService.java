package org.example.agrotrack.farming.application.queryservices;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.domain.model.queries.GetCropByIdQuery;
import org.example.agrotrack.farming.domain.model.queries.ListCropsQuery;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

import java.util.List;

public interface CropQueryService {

    Result<List<Crop>, ApplicationError> handle(ListCropsQuery query);

    Result<Crop, ApplicationError> handle(GetCropByIdQuery query);
}
