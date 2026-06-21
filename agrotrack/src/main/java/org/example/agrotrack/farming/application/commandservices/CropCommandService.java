package org.example.agrotrack.farming.application.commandservices;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.domain.model.commands.CreateCropCommand;
import org.example.agrotrack.farming.domain.model.commands.DeleteCropCommand;
import org.example.agrotrack.farming.domain.model.commands.UpdateCropCommand;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface CropCommandService {

    Result<Crop, ApplicationError> handle(CreateCropCommand command);

    Result<Crop, ApplicationError> handle(UpdateCropCommand command);

    Result<String, ApplicationError> handle(DeleteCropCommand command);
}
