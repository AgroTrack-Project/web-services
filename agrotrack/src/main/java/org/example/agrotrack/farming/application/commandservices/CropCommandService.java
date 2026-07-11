package org.example.agrotrack.farming.application.commandservices;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.domain.model.commands.CreateCropCommand;
import org.example.agrotrack.farming.domain.model.commands.DeleteCropCommand;
import org.example.agrotrack.farming.domain.model.commands.HarvestCropCommand;
import org.example.agrotrack.farming.domain.model.commands.UpdateCropCommand;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

/**
 * Application-layer port for crop write operations. Each overload handles one command type
 * and returns a {@link Result} instead of throwing, so controllers can map failures to HTTP
 * responses uniformly via {@code ResponseEntityAssembler}.
 */
public interface CropCommandService {

    Result<Crop, ApplicationError> handle(CreateCropCommand command);

    Result<Crop, ApplicationError> handle(UpdateCropCommand command);

    Result<Crop, ApplicationError> handle(HarvestCropCommand command);

    Result<String, ApplicationError> handle(DeleteCropCommand command);
}
