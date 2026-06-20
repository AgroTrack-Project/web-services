package org.example.agrotrack.soilmonitoring.application.commandservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.soilmonitoring.domain.model.aggregates.IrrigationRecommendation;
import org.example.agrotrack.soilmonitoring.domain.model.commands.CreateIrrigationRecommendationCommand;
import org.example.agrotrack.soilmonitoring.domain.model.commands.DeleteIrrigationRecommendationCommand;
import org.example.agrotrack.soilmonitoring.domain.model.commands.UpdateIrrigationRecommendationCommand;

public interface IrrigationRecommendationCommandService {

    Result<IrrigationRecommendation, ApplicationError> handle(CreateIrrigationRecommendationCommand command);

    Result<IrrigationRecommendation, ApplicationError> handle(UpdateIrrigationRecommendationCommand command);

    Result<String, ApplicationError> handle(DeleteIrrigationRecommendationCommand command);
}