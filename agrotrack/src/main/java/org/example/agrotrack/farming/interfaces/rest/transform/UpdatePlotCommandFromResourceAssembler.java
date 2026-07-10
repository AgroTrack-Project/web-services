package org.example.agrotrack.farming.interfaces.rest.transform;

import org.example.agrotrack.farming.domain.model.commands.UpdatePlotCommand;
import org.example.agrotrack.farming.interfaces.rest.resource.UpdatePlotResource;

/**
 * Maps an incoming {@code UpdatePlotResource} to the domain-level {@code UpdatePlotCommand}.
 * The plot id comes from the path variable rather than the request body.
 */
public final class UpdatePlotCommandFromResourceAssembler {

    private UpdatePlotCommandFromResourceAssembler() {
    }

    public static UpdatePlotCommand toCommandFromResource(String id, UpdatePlotResource resource) {
        return new UpdatePlotCommand(
                id,
                resource.name(),
                resource.location(),
                resource.sizeHectares()
        );
    }
}
