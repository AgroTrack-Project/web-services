package org.example.agrotrack.soilmonitoring.interfaces.acl;

import java.util.List;

public interface SoilMonitoringContextFacade {

    boolean deleteMonitoringDataByPlotId(String plotId);

    List<SoilRecordSummary> getSoilRecordsForPlot(String plotId);

    List<IrrigationRecommendationSummary> getIrrigationRecommendationsForPlot(String plotId);
}