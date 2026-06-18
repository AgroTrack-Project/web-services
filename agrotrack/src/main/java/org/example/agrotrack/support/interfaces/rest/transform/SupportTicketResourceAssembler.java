package org.example.agrotrack.support.interfaces.rest.transform;

import org.example.agrotrack.support.domain.model.aggregates.SupportTicket;
import org.example.agrotrack.support.interfaces.rest.resource.SupportTicketResource;

public final class SupportTicketResourceAssembler {

    private SupportTicketResourceAssembler() {
    }

    public static SupportTicketResource toResource(SupportTicket ticket) {
        return new SupportTicketResource(
                ticket.getId(),
                ticket.getUserId().value(),
                ticket.getSubject().value(),
                ticket.getMessage().value(),
                ticket.getStatus().name(),
                ticket.getCreatedAt(),
                ticket.getRespondedAt()
        );
    }
}
