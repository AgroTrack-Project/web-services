package org.example.agrotrack.support.interfaces.rest.transform;

import org.example.agrotrack.support.domain.model.commands.CloseSupportTicketCommand;
import org.example.agrotrack.support.interfaces.rest.resource.CloseSupportTicketResource;

public final class CloseSupportTicketCommandFromResourceAssembler {

    private CloseSupportTicketCommandFromResourceAssembler() {
    }

    public static CloseSupportTicketCommand toCommandFromResource(
            String ticketId,
            CloseSupportTicketResource resource
    ) {
        String requestedStatus = resource != null ? resource.status() : null;
        return new CloseSupportTicketCommand(ticketId, requestedStatus);
    }
}
