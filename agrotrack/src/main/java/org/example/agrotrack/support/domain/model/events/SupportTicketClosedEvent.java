package org.example.agrotrack.support.domain.model.events;

import org.example.agrotrack.support.domain.model.aggregates.SupportTicket;

import java.time.Instant;

public record SupportTicketClosedEvent(
        String ticketId,
        String userId,
        String subject,
        Instant respondedAt
) {

    public static SupportTicketClosedEvent from(SupportTicket ticket) {
        return new SupportTicketClosedEvent(
                ticket.getId(),
                ticket.getUserId().value(),
                ticket.getSubject().value(),
                ticket.getRespondedAt()
        );
    }
}
