package org.example.agrotrack.support.domain.model.events;

import org.example.agrotrack.support.domain.model.aggregates.SupportTicket;

import java.time.Instant;

public record SupportTicketCreatedEvent(
        String ticketId,
        String userId,
        String subject,
        String status,
        Instant createdAt
) {

    public static SupportTicketCreatedEvent from(SupportTicket ticket) {
        return new SupportTicketCreatedEvent(
                ticket.getId(),
                ticket.getUserId().value(),
                ticket.getSubject().value(),
                ticket.getStatus().name(),
                ticket.getCreatedAt()
        );
    }
}
