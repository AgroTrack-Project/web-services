package org.example.agrotrack.dashboard.application.commandservices;

import org.example.agrotrack.dashboard.domain.model.aggregates.YieldSummary;
import org.example.agrotrack.dashboard.domain.model.commands.CreateYieldSummaryCommand;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface YieldSummaryCommandService {

    Result<YieldSummary, ApplicationError> handle(CreateYieldSummaryCommand command);
}
