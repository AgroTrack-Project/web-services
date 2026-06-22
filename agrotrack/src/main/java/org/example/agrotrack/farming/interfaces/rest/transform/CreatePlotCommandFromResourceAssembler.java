package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.commands.CreatePlotCommand;
import org.example.agrotrack.farming.interfaces.rest.resource.CreatePlotResource;

public final class CreatePlotCommandFromResourceAssembler {

    private CreatePlotCommandFromResourceAssembler() {
    }

    public static CreatePlotCommand toCommandFromResource(CreatePlotResource resource) {
        return new CreatePlotCommand(
                resource.userId(),
                resource.name(),
                resource.location(),
                resource.sizeHectares()
        );
    }
}
