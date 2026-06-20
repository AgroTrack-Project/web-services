package org.example.agrotrack.soilmonitoring.application.acl;

import org.example.agrotrack.soilmonitoring.application.commandservices.SoilMonitoringDataCommandService;
import org.example.agrotrack.soilmonitoring.domain.model.commands.DeleteSoilMonitoringDataByPlotIdCommand;
import org.example.agrotrack.soilmonitoring.interfaces.acl.SoilMonitoringContextFacade;
import org.springframework.stereotype.Service;

@Service
public class SoilMonitoringContextFacadeImpl implements SoilMonitoringContextFacade {

    private final SoilMonitoringDataCommandService soilMonitoringDataCommandService;

    public SoilMonitoringContextFacadeImpl(
            SoilMonitoringDataCommandService soilMonitoringDataCommandService
    ) {
        this.soilMonitoringDataCommandService = soilMonitoringDataCommandService;
    }

    @Override
    public boolean deleteMonitoringDataByPlotId(String plotId) {
        var result = soilMonitoringDataCommandService.handle(
                new DeleteSoilMonitoringDataByPlotIdCommand(plotId)
        );

        return result.isSuccess();
    }
}