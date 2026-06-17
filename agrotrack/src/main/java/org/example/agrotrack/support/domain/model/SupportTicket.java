package org.example.agrotrack.support.domain.model;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.time.Instant;
import java.util.Objects;

public class SupportTicket extends AbstractDomainAggregateRoot<SupportTicket> {

    private final String id;
    private final String userId;
    private final String subject;
    private final String message;
    private TicketStatus status;
    private final Instant createdAt;
    private Instant respondedAt;

    private SupportTicket(
            String id,
            String userId,
            String subject,
            String message,
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

    public static SupportTicket open(String userId, String subject, String message, Instant createdAt) {
        return new SupportTicket(null, userId, subject, message, TicketStatus.OPEN, createdAt, null);
    }

    public static SupportTicket restore(
            String id,
            String userId,
            String subject,
            String message,
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
            throw new IllegalStateException("Support ticket is already closed");
        }
        this.status = TicketStatus.CLOSED;
        this.respondedAt = Objects.requireNonNull(respondedAt, "respondedAt must not be null");
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
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
