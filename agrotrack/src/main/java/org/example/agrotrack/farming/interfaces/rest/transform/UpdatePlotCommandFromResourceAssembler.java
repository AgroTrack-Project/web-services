package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.commands.UpdatePlotCommand;
import org.example.agrotrack.farming.interfaces.rest.resource.UpdatePlotResource;

public final class UpdatePlotCommandFromResourceAssembler {

    private UpdatePlotCommandFromResourceAssembler() {
    }

    public static UpdatePlotCommand toCommandFromResource(Long id, UpdatePlotResource resource) {
        return new UpdatePlotCommand(
                id,
                resource.name(),
                resource.location(),
                resource.sizeHectares()
        );
    }
}
