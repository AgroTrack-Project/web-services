package org.example.agrotrack.support.domain.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SupportTicketTest {

    @Test
    void openTicketStartsAsOpenWithoutRespondedAt() {
        SupportTicket ticket = SupportTicket.open("user-1", "Subject", "Message", Instant.parse("2026-01-01T00:00:00Z"));

        assertEquals(TicketStatus.OPEN, ticket.getStatus());
        assertTrue(ticket.canBeClosed());
        assertEquals(null, ticket.getRespondedAt());
    }

    @Test
    void closeSetsStatusAndRespondedAt() {
        SupportTicket ticket = SupportTicket.open("user-1", "Subject", "Message", Instant.parse("2026-01-01T00:00:00Z"));
        Instant respondedAt = Instant.parse("2026-01-02T00:00:00Z");

        ticket.close(respondedAt);

        assertEquals(TicketStatus.CLOSED, ticket.getStatus());
        assertEquals(respondedAt, ticket.getRespondedAt());
    }

    @Test
    void closeThrowsWhenAlreadyClosed() {
        SupportTicket ticket = SupportTicket.restore(
                "ticket-1",
                "user-1",
                "Subject",
                "Message",
                TicketStatus.CLOSED,
                Instant.parse("2026-01-01T00:00:00Z"),
                Instant.parse("2026-01-02T00:00:00Z")
        );

        assertThrows(IllegalStateException.class, () -> ticket.close(Instant.now()));
    }
}
