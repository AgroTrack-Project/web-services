package org.example.agrotrack.farming.application.internal.queryservices;

import org.example.agrotrack.farming.application.queryservices.CropQueryService;
import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.domain.model.queries.GetCropByIdQuery;
import org.example.agrotrack.farming.domain.model.queries.ListCropsQuery;
import org.example.agrotrack.farming.domain.repositories.CropRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CropQueryServiceImpl implements CropQueryService {

    private final CropRepository repository;

    public CropQueryServiceImpl(CropRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<List<Crop>, ApplicationError> handle(ListCropsQuery query) {
        Long plotId = query.plotId();

        if (plotId != null) {
            return Result.success(repository.findByPlotId(plotId));
        }

        return Result.success(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Result<Crop, ApplicationError> handle(GetCropByIdQuery query) {
        Long id = query.id();

        var cropOptional = repository.findById(id);

        if (cropOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Crop", String.valueOf(id)));
        }

        return Result.success(cropOptional.get());
    }
}
