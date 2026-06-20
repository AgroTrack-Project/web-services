package org.example.agrotrack.soilmonitoring.application.internal.commandservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.soilmonitoring.application.commandservices.SoilRecordCommandService;
import org.example.agrotrack.soilmonitoring.application.ports.PlotReferencePort;
import org.example.agrotrack.soilmonitoring.domain.model.aggregates.SoilRecord;
import org.example.agrotrack.soilmonitoring.domain.model.commands.CreateSoilRecordCommand;
import org.example.agrotrack.soilmonitoring.domain.model.commands.DeleteSoilRecordCommand;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.Humidity;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.PlotId;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.Temperature;
import org.example.agrotrack.soilmonitoring.domain.repositories.SoilRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class SoilRecordCommandServiceImpl implements SoilRecordCommandService {

    private final SoilRecordRepository repository;
    private final PlotReferencePort plotReferencePort;

    public SoilRecordCommandServiceImpl(
            SoilRecordRepository repository,
            PlotReferencePort plotReferencePort
    ) {
        this.repository = repository;
        this.plotReferencePort = plotReferencePort;
    }

    @Override
    @Transactional
    public Result<SoilRecord, ApplicationError> handle(CreateSoilRecordCommand command) {
        try {
            PlotId plotId = new PlotId(command.plotId());
            Humidity humidity = new Humidity(command.humidity());
            Temperature temperature = new Temperature(command.temperature());

            if (!plotReferencePort.plotExists(plotId.value())) {
                return Result.failure(ApplicationError.validationError("plot_id", "Plot does not exist"));
            }

            Instant recordedAt = command.recordedAt() != null
                    ? command.recordedAt()
                    : Instant.now();

            SoilRecord soilRecord = SoilRecord.create(plotId, humidity, temperature, recordedAt);

            return Result.success(repository.save(soilRecord));
        } catch (IllegalArgumentException ex) {
            return Result.failure(ApplicationError.validationError("SoilRecord", ex.getMessage()));
        }
    }

    @Override
    @Transactional
    public Result<String, ApplicationError> handle(DeleteSoilRecordCommand command) {
        String id = command.id();

        if (id == null || id.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "soil record id is required"));
        }

        var soilRecordOptional = repository.findById(id);

        if (soilRecordOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("SoilRecord", id));
        }

        repository.deleteById(id);

        return Result.success("Soil record deleted successfully");
    }
}