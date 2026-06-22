package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.aggregates.Plot;
import org.example.agrotrack.farming.interfaces.rest.resource.PlotResource;

public final class PlotResourceAssembler {

    private PlotResourceAssembler() {
    }

    public static PlotResource toResource(Plot plot) {
        return new PlotResource(
                plot.getId(),
                plot.getUserId(),
                plot.getName(),
                plot.getLocation(),
                plot.getSizeHectares(),
                plot.getStatus().name(),
                plot.getCreatedAt()
        );
    }
}
