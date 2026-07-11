package org.example.agrotrack.dashboard.application.internal.commandservices;

import org.example.agrotrack.dashboard.application.commandservices.LossSummaryCommandService;
import org.example.agrotrack.dashboard.application.ports.PlotOwnershipQueryPort;
import org.example.agrotrack.dashboard.domain.model.aggregates.LossSummary;
import org.example.agrotrack.dashboard.domain.model.commands.CreateLossSummaryCommand;
import org.example.agrotrack.dashboard.domain.model.valueobjects.LossCause;
import org.example.agrotrack.dashboard.domain.model.valueobjects.LossPercentage;
import org.example.agrotrack.dashboard.domain.model.valueobjects.PlotId;
import org.example.agrotrack.dashboard.domain.model.valueobjects.Season;
import org.example.agrotrack.dashboard.domain.repositories.LossSummaryRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LossSummaryCommandServiceImpl implements LossSummaryCommandService {

    private final LossSummaryRepository repository;
    private final PlotOwnershipQueryPort plotOwnershipQueryPort;

    public LossSummaryCommandServiceImpl(
            LossSummaryRepository repository,
            PlotOwnershipQueryPort plotOwnershipQueryPort
    ) {
        this.repository = repository;
        this.plotOwnershipQueryPort = plotOwnershipQueryPort;
    }

    @Override
    @Transactional
    public Result<LossSummary, ApplicationError> handle(CreateLossSummaryCommand command) {
        if (plotOwnershipQueryPort.findPlotById(command.plotId()).isEmpty()) {
            return Result.failure(ApplicationError.validationError("plot_id", "Plot does not exist"));
        }

        try {
            LossSummary summary = LossSummary.create(
                    new PlotId(command.plotId()),
                    new LossPercentage(command.lossPercentage()),
                    new LossCause(command.cause()),
                    new Season(command.season())
            );
            return Result.success(repository.save(summary));
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("LossSummary", e.getMessage()));
        }
    }
}
