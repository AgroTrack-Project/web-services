package org.example.agrotrack.support.interfaces.rest.transform;

import org.example.agrotrack.support.domain.model.commands.CreateSupportTicketCommand;
import org.example.agrotrack.support.interfaces.rest.resource.CreateSupportTicketResource;

public final class CreateSupportTicketCommandFromResourceAssembler {

    private CreateSupportTicketCommandFromResourceAssembler() {
    }

    public static CreateSupportTicketCommand toCommandFromResource(CreateSupportTicketResource resource) {
        return new CreateSupportTicketCommand(
                resource.userId(),
                resource.subject(),
                resource.message()
        );
    }
}
