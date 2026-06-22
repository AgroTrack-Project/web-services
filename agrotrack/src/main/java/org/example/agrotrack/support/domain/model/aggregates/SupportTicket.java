package org.example.agrotrack.support.domain.model.aggregates;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;
import org.example.agrotrack.support.domain.model.TicketStatus;
import org.example.agrotrack.support.domain.model.events.SupportTicketClosedEvent;
import org.example.agrotrack.support.domain.model.events.SupportTicketCreatedEvent;
import org.example.agrotrack.support.domain.model.valueobjects.TicketMessage;
import org.example.agrotrack.support.domain.model.valueobjects.TicketSubject;
import org.example.agrotrack.support.domain.model.valueobjects.UserId;

import java.time.Instant;
import java.util.Objects;

public class SupportTicket extends AbstractDomainAggregateRoot<SupportTicket> {

    private final String id;
    private final UserId userId;
    private final TicketSubject subject;
    private final TicketMessage message;
    private TicketStatus status;
    private final Instant createdAt;
    private Instant respondedAt;

    private SupportTicket(
            String id,
            UserId userId,
            TicketSubject subject,
            TicketMessage message,
            TicketStatus status,
            Instant createdAt,
            Instant respondedAt
    ) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        this.subject = Objects.requireNonNull(subject, "subject must not be null");
        this.message = Objects.requireNonNull(message, "message must not be null");
        this.status = Objects.requireNonNull(status, "status must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.respondedAt = respondedAt;
    }

    public static SupportTicket open(UserId userId, TicketSubject subject, TicketMessage message, Instant createdAt) {
        return new SupportTicket(null, userId, subject, message, TicketStatus.OPEN, createdAt, null);
    }

    public static SupportTicket restore(
            String id,
            UserId userId,
            TicketSubject subject,
            TicketMessage message,
            TicketStatus status,
            Instant createdAt,
            Instant respondedAt
    ) {
        return new SupportTicket(id, userId, subject, message, status, createdAt, respondedAt);
    }

    public boolean isClosed() {
        return status == TicketStatus.CLOSED;
    }

    public boolean canBeClosed() {
        return status == TicketStatus.OPEN || status == TicketStatus.IN_PROGRESS;
    }

    public void close(Instant respondedAt) {
        if (isClosed()) {
            throw new IllegalStateException("support.ticket.error.already-closed");
        }
        this.status = TicketStatus.CLOSED;
        this.respondedAt = Objects.requireNonNull(respondedAt, "respondedAt must not be null");
        onClosed();
    }

    public void onCreated() {
        registerDomainEvent(SupportTicketCreatedEvent.from(this));
    }

    public void onClosed() {
        registerDomainEvent(SupportTicketClosedEvent.from(this));
    }

    public String getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public TicketSubject getSubject() {
        return subject;
    }

    public TicketMessage getMessage() {
        return message;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getRespondedAt() {
        return respondedAt;
    }
}
