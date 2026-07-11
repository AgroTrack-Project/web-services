package org.example.agrotrack.soilmonitoring.application.acl;

import org.example.agrotrack.soilmonitoring.application.commandservices.SoilMonitoringDataCommandService;
import org.example.agrotrack.soilmonitoring.domain.model.commands.DeleteSoilMonitoringDataByPlotIdCommand;
import org.example.agrotrack.soilmonitoring.domain.repositories.IrrigationRecommendationRepository;
import org.example.agrotrack.soilmonitoring.domain.repositories.SoilRecordRepository;
import org.example.agrotrack.soilmonitoring.interfaces.acl.IrrigationRecommendationSummary;
import org.example.agrotrack.soilmonitoring.interfaces.acl.SoilMonitoringContextFacade;
import org.example.agrotrack.soilmonitoring.interfaces.acl.SoilRecordSummary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoilMonitoringContextFacadeImpl implements SoilMonitoringContextFacade {

    private final SoilMonitoringDataCommandService soilMonitoringDataCommandService;
    private final SoilRecordRepository soilRecordRepository;
    private final IrrigationRecommendationRepository irrigationRecommendationRepository;

    public SoilMonitoringContextFacadeImpl(
            SoilMonitoringDataCommandService soilMonitoringDataCommandService,
            SoilRecordRepository soilRecordRepository,
            IrrigationRecommendationRepository irrigationRecommendationRepository
    ) {
        this.soilMonitoringDataCommandService = soilMonitoringDataCommandService;
        this.soilRecordRepository = soilRecordRepository;
        this.irrigationRecommendationRepository = irrigationRecommendationRepository;
    }

    @Override
    public boolean deleteMonitoringDataByPlotId(String plotId) {
        var result = soilMonitoringDataCommandService.handle(
                new DeleteSoilMonitoringDataByPlotIdCommand(plotId)
        );

        return result.isSuccess();
    }

    @Override
    public List<SoilRecordSummary> getSoilRecordsForPlot(String plotId) {
        return soilRecordRepository.findByPlotIdOrderByRecordedAtDesc(plotId).stream()
                .map(record -> new SoilRecordSummary(
                        record.getId(),
                        record.getHumidity().value(),
                        record.getTemperature().value(),
                        record.getStatus().name(),
                        record.getRecordedAt()
                ))
                .toList();
    }

    @Override
    public List<IrrigationRecommendationSummary> getIrrigationRecommendationsForPlot(String plotId) {
        return irrigationRecommendationRepository.findByPlotIdOrderByGeneratedAtDesc(plotId).stream()
                .map(recommendation -> new IrrigationRecommendationSummary(
                        recommendation.getId(),
                        recommendation.getUrgency().name(),
                        recommendation.getStatus().name(),
                        recommendation.getGeneratedAt()
                ))
                .toList();
    }
}