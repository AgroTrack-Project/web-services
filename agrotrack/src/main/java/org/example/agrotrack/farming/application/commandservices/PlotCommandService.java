package org.example.agrotrack.farming.application.commandservices;

import org.example.agrotrack.farming.domain.model.aggregates.Plot;
import org.example.agrotrack.farming.domain.model.commands.CreatePlotCommand;
import org.example.agrotrack.farming.domain.model.commands.DeletePlotCommand;
import org.example.agrotrack.farming.domain.model.commands.UpdatePlotCommand;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface PlotCommandService {

    Result<Plot, ApplicationError> handle(CreatePlotCommand command);

    Result<Plot, ApplicationError> handle(UpdatePlotCommand command);

    Result<String, ApplicationError> handle(DeletePlotCommand command);
}
