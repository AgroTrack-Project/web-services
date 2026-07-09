package org.example.agrotrack.dashboard.application.commandservices;

import org.example.agrotrack.dashboard.domain.model.aggregates.LossSummary;
import org.example.agrotrack.dashboard.domain.model.commands.CreateLossSummaryCommand;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface LossSummaryCommandService {

    Result<LossSummary, ApplicationError> handle(CreateLossSummaryCommand command);
}
