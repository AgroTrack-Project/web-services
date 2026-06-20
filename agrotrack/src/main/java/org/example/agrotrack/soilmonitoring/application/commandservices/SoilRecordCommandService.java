package org.example.agrotrack.soilmonitoring.application.commandservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.soilmonitoring.domain.model.aggregates.SoilRecord;
import org.example.agrotrack.soilmonitoring.domain.model.commands.CreateSoilRecordCommand;
import org.example.agrotrack.soilmonitoring.domain.model.commands.DeleteSoilRecordCommand;

public interface SoilRecordCommandService {

    Result<SoilRecord, ApplicationError> handle(CreateSoilRecordCommand command);

    Result<String, ApplicationError> handle(DeleteSoilRecordCommand command);
}