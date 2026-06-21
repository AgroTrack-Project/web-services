package org.example.agrotrack.farming.application.internal.commandservices;

import org.example.agrotrack.farming.application.commandservices.PlotCommandService;
import org.example.agrotrack.farming.domain.model.aggregates.Plot;
import org.example.agrotrack.farming.domain.model.commands.CreatePlotCommand;
import org.example.agrotrack.farming.domain.model.commands.DeactivatePlotCommand;
import org.example.agrotrack.farming.domain.model.commands.UpdatePlotCommand;
import org.example.agrotrack.farming.domain.repositories.PlotRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlotCommandServiceImpl implements PlotCommandService {

    private final PlotRepository repository;

    public PlotCommandServiceImpl(PlotRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Result<Plot, ApplicationError> handle(CreatePlotCommand command) {
        Plot plot = Plot.create(command.userId(), command.name(), command.location(), command.sizeHectares());

        return Result.success(repository.save(plot));
    }

    @Override
    @Transactional
    public Result<Plot, ApplicationError> handle(UpdatePlotCommand command) {
        var plotOptional = repository.findById(command.id());

        if (plotOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Plot", String.valueOf(command.id())));
        }

        Plot plot = plotOptional.get();
        plot.update(command.name(), command.location(), command.sizeHectares());

        return Result.success(repository.save(plot));
    }

    @Override
    @Transactional
    public Result<Plot, ApplicationError> handle(DeactivatePlotCommand command) {
        var plotOptional = repository.findById(command.id());

        if (plotOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Plot", String.valueOf(command.id())));
        }

        Plot plot = plotOptional.get();
        plot.deactivate();

        return Result.success(repository.save(plot));
    }
}
