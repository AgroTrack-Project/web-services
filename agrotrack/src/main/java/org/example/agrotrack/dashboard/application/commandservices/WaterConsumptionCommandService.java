package org.example.agrotrack.dashboard.application.commandservices;

import org.example.agrotrack.dashboard.domain.model.aggregates.WaterConsumption;
import org.example.agrotrack.dashboard.domain.model.commands.CreateWaterConsumptionCommand;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface WaterConsumptionCommandService {

    Result<WaterConsumption, ApplicationError> handle(CreateWaterConsumptionCommand command);
}
