package org.example.agrotrack.farming.application.internal.commandservices;

import org.example.agrotrack.farming.application.commandservices.CropCommandService;
import org.example.agrotrack.farming.application.internal.services.HarvestMetricsGenerator;
import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.domain.model.commands.CreateCropCommand;
import org.example.agrotrack.farming.domain.model.commands.DeleteCropCommand;
import org.example.agrotrack.farming.domain.model.commands.HarvestCropCommand;
import org.example.agrotrack.farming.domain.model.commands.UpdateCropCommand;
import org.example.agrotrack.farming.domain.repositories.CropRepository;
import org.example.agrotrack.farming.domain.repositories.PlotRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CropCommandServiceImpl implements CropCommandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CropCommandServiceImpl.class);

    private final CropRepository repository;
    private final PlotRepository plotRepository;
    private final HarvestMetricsGenerator harvestMetricsGenerator;

    public CropCommandServiceImpl(
            CropRepository repository,
            PlotRepository plotRepository,
            HarvestMetricsGenerator harvestMetricsGenerator
    ) {
        this.repository = repository;
        this.plotRepository = plotRepository;
        this.harvestMetricsGenerator = harvestMetricsGenerator;
    }

    @Override
    @Transactional
    public Result<Crop, ApplicationError> handle(CreateCropCommand command) {
        if (plotRepository.findById(command.plotId()).isEmpty()) {
            return Result.failure(ApplicationError.validationError("plot_id", "Plot does not exist"));
        }

        Crop crop = Crop.create(command.plotId(), command.type(), command.sowingDate(), command.harvestDate());

        return Result.success(repository.save(crop));
    }

    @Override
    @Transactional
    public Result<Crop, ApplicationError> handle(UpdateCropCommand command) {
        var cropOptional = repository.findById(command.id());

        if (cropOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Crop", command.id()));
        }

        Crop crop = cropOptional.get();
        crop.update(command.type(), command.sowingDate(), command.harvestDate());

        return Result.success(repository.save(crop));
    }

    @Override
    @Transactional
    public Result<Crop, ApplicationError> handle(HarvestCropCommand command) {
        var cropOptional = repository.findById(command.id());

        if (cropOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Crop", command.id()));
        }

        Crop crop = cropOptional.get();
        try {
            crop.harvest(command.harvestDate());
        } catch (IllegalStateException ex) {
            return Result.failure(ApplicationError.businessRuleViolation("crop_harvest", ex.getMessage()));
        }

        Crop harvestedCrop = repository.save(crop);

        try {
            harvestMetricsGenerator.generateForHarvest(
                    harvestedCrop.getPlotId(),
                    harvestedCrop.getSowingDate(),
                    harvestedCrop.getHarvestDate()
            );
        } catch (Exception ex) {
            LOGGER.warn("Failed to auto-generate dashboard metrics for harvested crop {}", harvestedCrop.getId(), ex);
        }

        return Result.success(harvestedCrop);
    }

    @Override
    @Transactional
    public Result<String, ApplicationError> handle(DeleteCropCommand command) {
        var cropOptional = repository.findById(command.id());

        if (cropOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Crop", command.id()));
        }

        repository.deleteById(command.id());

        return Result.success("Crop deleted successfully");
    }
}
