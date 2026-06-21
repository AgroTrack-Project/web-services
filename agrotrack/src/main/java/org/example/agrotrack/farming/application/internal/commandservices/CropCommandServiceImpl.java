package org.example.agrotrack.farming.application.internal.commandservices;

import org.example.agrotrack.farming.application.commandservices.CropCommandService;
import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.domain.model.commands.CreateCropCommand;
import org.example.agrotrack.farming.domain.model.commands.DeleteCropCommand;
import org.example.agrotrack.farming.domain.model.commands.UpdateCropCommand;
import org.example.agrotrack.farming.domain.repositories.CropRepository;
import org.example.agrotrack.farming.domain.repositories.PlotRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CropCommandServiceImpl implements CropCommandService {

    private final CropRepository repository;
    private final PlotRepository plotRepository;

    public CropCommandServiceImpl(CropRepository repository, PlotRepository plotRepository) {
        this.repository = repository;
        this.plotRepository = plotRepository;
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
    public Result<String, ApplicationError> handle(DeleteCropCommand command) {
        var cropOptional = repository.findById(command.id());

        if (cropOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Crop", command.id()));
        }

        repository.deleteById(command.id());

        return Result.success("Crop deleted successfully");
    }
}
