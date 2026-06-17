package org.example.agrotrack.support.interfaces.transform;

import org.example.agrotrack.support.domain.model.SupportTicket;
import org.example.agrotrack.support.interfaces.resources.SupportTicketResource;
import org.springframework.stereotype.Component;

@Component
public class SupportTicketResourceAssembler {

    public SupportTicketResource toResource(SupportTicket ticket) {
        return new SupportTicketResource(
                ticket.getId(),
                ticket.getUserId(),
                ticket.getSubject(),
                ticket.getMessage(),
                ticket.getStatus().name(),
                ticket.getCreatedAt(),
                ticket.getRespondedAt()
        );
    }
}
