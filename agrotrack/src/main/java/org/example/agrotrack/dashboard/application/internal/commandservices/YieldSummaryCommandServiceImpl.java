package org.example.agrotrack.dashboard.application.internal.commandservices;

import org.example.agrotrack.dashboard.application.commandservices.YieldSummaryCommandService;
import org.example.agrotrack.dashboard.application.ports.PlotOwnershipQueryPort;
import org.example.agrotrack.dashboard.domain.model.aggregates.YieldSummary;
import org.example.agrotrack.dashboard.domain.model.commands.CreateYieldSummaryCommand;
import org.example.agrotrack.dashboard.domain.model.valueobjects.PlotId;
import org.example.agrotrack.dashboard.domain.model.valueobjects.Season;
import org.example.agrotrack.dashboard.domain.model.valueobjects.YieldPerHectare;
import org.example.agrotrack.dashboard.domain.repositories.YieldSummaryRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class YieldSummaryCommandServiceImpl implements YieldSummaryCommandService {

    private final YieldSummaryRepository repository;
    private final PlotOwnershipQueryPort plotOwnershipQueryPort;

    public YieldSummaryCommandServiceImpl(
            YieldSummaryRepository repository,
            PlotOwnershipQueryPort plotOwnershipQueryPort
    ) {
        this.repository = repository;
        this.plotOwnershipQueryPort = plotOwnershipQueryPort;
    }

    @Override
    @Transactional
    public Result<YieldSummary, ApplicationError> handle(CreateYieldSummaryCommand command) {
        if (plotOwnershipQueryPort.findPlotById(command.plotId()).isEmpty()) {
            return Result.failure(ApplicationError.validationError("plot_id", "Plot does not exist"));
        }

        try {
            YieldSummary summary = YieldSummary.create(
                    new PlotId(command.plotId()),
                    new YieldPerHectare(command.yieldPerHectare()),
                    new Season(command.season())
            );
            return Result.success(repository.save(summary));
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("YieldSummary", e.getMessage()));
        }
    }
}
