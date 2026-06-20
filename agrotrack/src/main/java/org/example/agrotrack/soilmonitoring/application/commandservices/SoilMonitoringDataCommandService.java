package org.example.agrotrack.soilmonitoring.application.commandservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.soilmonitoring.domain.model.commands.DeleteSoilMonitoringDataByPlotIdCommand;

public interface SoilMonitoringDataCommandService {

    Result<String, ApplicationError> handle(DeleteSoilMonitoringDataByPlotIdCommand command);
}