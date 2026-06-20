package org.example.agrotrack.soilmonitoring.application.internal.commandservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.soilmonitoring.application.commandservices.SoilMonitoringDataCommandService;
import org.example.agrotrack.soilmonitoring.domain.model.commands.DeleteSoilMonitoringDataByPlotIdCommand;
import org.example.agrotrack.soilmonitoring.domain.repositories.IrrigationRecommendationRepository;
import org.example.agrotrack.soilmonitoring.domain.repositories.SoilRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SoilMonitoringDataCommandServiceImpl implements SoilMonitoringDataCommandService {

    private final SoilRecordRepository soilRecordRepository;
    private final IrrigationRecommendationRepository irrigationRecommendationRepository;

    public SoilMonitoringDataCommandServiceImpl(
            SoilRecordRepository soilRecordRepository,
            IrrigationRecommendationRepository irrigationRecommendationRepository
    ) {
        this.soilRecordRepository = soilRecordRepository;
        this.irrigationRecommendationRepository = irrigationRecommendationRepository;
    }

    @Override
    @Transactional
    public Result<String, ApplicationError> handle(DeleteSoilMonitoringDataByPlotIdCommand command) {
        String plotId = command.plotId();

        if (plotId == null || plotId.isBlank()) {
            return Result.failure(
                    ApplicationError.validationError("plot_id", "plot_id is required")
            );
        }

        String trimmedPlotId = plotId.trim();

        try {
            irrigationRecommendationRepository.deleteByPlotId(trimmedPlotId);
            soilRecordRepository.deleteByPlotId(trimmedPlotId);

            return Result.success("Soil monitoring data deleted successfully");
        } catch (RuntimeException ex) {
            return Result.failure(
                    ApplicationError.unexpected(
                            "Delete soil monitoring data by plot_id",
                            ex.getMessage()
                    )
            );
        }
    }
}