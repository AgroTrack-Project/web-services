package org.example.agrotrack.soilmonitoring.application.internal.commandservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.soilmonitoring.application.commandservices.IrrigationRecommendationCommandService;
import org.example.agrotrack.soilmonitoring.application.ports.PlotReferencePort;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.IrrigationRecommendationStatus;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.IrrigationUrgency;
import org.example.agrotrack.soilmonitoring.domain.model.aggregates.IrrigationRecommendation;
import org.example.agrotrack.soilmonitoring.domain.model.commands.CreateIrrigationRecommendationCommand;
import org.example.agrotrack.soilmonitoring.domain.model.commands.DeleteIrrigationRecommendationCommand;
import org.example.agrotrack.soilmonitoring.domain.model.commands.UpdateIrrigationRecommendationCommand;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.PlotId;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.SoilRecordId;
import org.example.agrotrack.soilmonitoring.domain.repositories.IrrigationRecommendationRepository;
import org.example.agrotrack.soilmonitoring.domain.repositories.SoilRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class IrrigationRecommendationCommandServiceImpl implements IrrigationRecommendationCommandService {

    private final IrrigationRecommendationRepository repository;
    private final SoilRecordRepository soilRecordRepository;
    private final PlotReferencePort plotReferencePort;

    public IrrigationRecommendationCommandServiceImpl(
            IrrigationRecommendationRepository repository,
            SoilRecordRepository soilRecordRepository,
            PlotReferencePort plotReferencePort
    ) {
        this.repository = repository;
        this.soilRecordRepository = soilRecordRepository;
        this.plotReferencePort = plotReferencePort;
    }

    @Override
    @Transactional
    public Result<IrrigationRecommendation, ApplicationError> handle(CreateIrrigationRecommendationCommand command) {
        try {
            PlotId plotId = new PlotId(command.plotId());
            SoilRecordId soilRecordId = new SoilRecordId(command.soilRecordId());

            if (!plotReferencePort.plotExists(plotId.value())) {
                return Result.failure(ApplicationError.validationError("plot_id", "Plot does not exist"));
            }

            if (soilRecordRepository.findById(soilRecordId.value()).isEmpty()) {
                return Result.failure(ApplicationError.validationError("soil_record_id", "Soil record does not exist"));
            }

            IrrigationUrgency urgency = parseUrgency(command.urgency());
            IrrigationRecommendationStatus status = parseStatus(command.status());

            Instant generatedAt = command.generatedAt() != null
                    ? command.generatedAt()
                    : Instant.now();

            Instant respondedAt = status == IrrigationRecommendationStatus.PENDING
                    ? null
                    : command.respondedAt() != null ? command.respondedAt() : Instant.now();

            IrrigationRecommendation recommendation = IrrigationRecommendation.create(
                    plotId,
                    soilRecordId,
                    command.message(),
                    urgency,
                    status,
                    generatedAt,
                    respondedAt
            );

            return Result.success(repository.save(recommendation));
        } catch (IllegalArgumentException ex) {
            return Result.failure(ApplicationError.validationError("IrrigationRecommendation", ex.getMessage()));
        }
    }

    @Override
    @Transactional
    public Result<IrrigationRecommendation, ApplicationError> handle(UpdateIrrigationRecommendationCommand command) {
        String id = command.id();

        if (id == null || id.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "irrigation recommendation id is required"));
        }

        var recommendationOptional = repository.findById(id);

        if (recommendationOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("IrrigationRecommendation", id));
        }

        try {
            IrrigationRecommendation recommendation = recommendationOptional.get();
            IrrigationRecommendationStatus status = parseStatus(command.status());

            recommendation.updateStatus(status, command.respondedAt());

            return Result.success(repository.save(recommendation));
        } catch (IllegalArgumentException ex) {
            return Result.failure(ApplicationError.validationError("IrrigationRecommendation", ex.getMessage()));
        }
    }

    @Override
    @Transactional
    public Result<String, ApplicationError> handle(DeleteIrrigationRecommendationCommand command) {
        String id = command.id();

        if (id == null || id.isBlank()) {
            return Result.failure(ApplicationError.validationError("id", "irrigation recommendation id is required"));
        }

        var recommendationOptional = repository.findById(id);

        if (recommendationOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("IrrigationRecommendation", id));
        }

        repository.deleteById(id);

        return Result.success("Irrigation recommendation deleted successfully");
    }

    private IrrigationUrgency parseUrgency(String urgency) {
        if (urgency == null || urgency.isBlank()) {
            return IrrigationUrgency.LOW;
        }

        return IrrigationUrgency.valueOf(urgency.trim().toUpperCase());
    }

    private IrrigationRecommendationStatus parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return IrrigationRecommendationStatus.PENDING;
        }

        return IrrigationRecommendationStatus.valueOf(status.trim().toUpperCase());
    }
}