package org.example.agrotrack.soilmonitoring.infrastructure.adapters;

import org.example.agrotrack.soilmonitoring.application.ports.PlotReferencePort;
import org.springframework.stereotype.Component;

@Component
public class StubPlotReferenceAdapter implements PlotReferencePort {

    @Override
    public boolean plotExists(String plotId) {
        return plotId != null && !plotId.isBlank();
    }
}