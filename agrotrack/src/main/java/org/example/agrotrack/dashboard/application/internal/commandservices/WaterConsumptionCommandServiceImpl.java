package org.example.agrotrack.dashboard.application.internal.commandservices;

import org.example.agrotrack.dashboard.application.commandservices.WaterConsumptionCommandService;
import org.example.agrotrack.dashboard.application.ports.PlotOwnershipQueryPort;
import org.example.agrotrack.dashboard.domain.model.aggregates.WaterConsumption;
import org.example.agrotrack.dashboard.domain.model.commands.CreateWaterConsumptionCommand;
import org.example.agrotrack.dashboard.domain.model.valueobjects.PlotId;
import org.example.agrotrack.dashboard.domain.model.valueobjects.Season;
import org.example.agrotrack.dashboard.domain.model.valueobjects.TotalLiters;
import org.example.agrotrack.dashboard.domain.repositories.WaterConsumptionRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WaterConsumptionCommandServiceImpl implements WaterConsumptionCommandService {

    private final WaterConsumptionRepository repository;
    private final PlotOwnershipQueryPort plotOwnershipQueryPort;

    public WaterConsumptionCommandServiceImpl(
            WaterConsumptionRepository repository,
            PlotOwnershipQueryPort plotOwnershipQueryPort
    ) {
        this.repository = repository;
        this.plotOwnershipQueryPort = plotOwnershipQueryPort;
    }

    @Override
    @Transactional
    public Result<WaterConsumption, ApplicationError> handle(CreateWaterConsumptionCommand command) {
        if (plotOwnershipQueryPort.findPlotById(command.plotId()).isEmpty()) {
            return Result.failure(ApplicationError.validationError("plot_id", "Plot does not exist"));
        }

        try {
            WaterConsumption consumption = WaterConsumption.create(
                    new PlotId(command.plotId()),
                    new TotalLiters(command.totalLiters()),
                    new Season(command.season())
            );
            return Result.success(repository.save(consumption));
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("WaterConsumption", e.getMessage()));
        }
    }
}
