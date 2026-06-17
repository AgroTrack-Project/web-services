package org.example.agrotrack.support.interfaces.transform;

import org.example.agrotrack.support.domain.model.SupportTicket;
import org.example.agrotrack.support.infrastructure.persistence.jpa.entities.SupportTicketEntity;
import org.springframework.stereotype.Component;

@Component
public class SupportTicketEntityAssembler {

    public SupportTicketEntity toNewEntity(SupportTicket ticket) {
        SupportTicketEntity entity = new SupportTicketEntity();
        entity.setUserId(ticket.getUserId());
        entity.setSubject(ticket.getSubject());
        entity.setMessage(ticket.getMessage());
        entity.setStatus(ticket.getStatus());
        entity.setCreatedAt(ticket.getCreatedAt());
        entity.setRespondedAt(ticket.getRespondedAt());
        return entity;
    }

    public void updateEntityFromDomain(SupportTicketEntity entity, SupportTicket ticket) {
        entity.setStatus(ticket.getStatus());
        entity.setRespondedAt(ticket.getRespondedAt());
    }

    public SupportTicket toDomain(SupportTicketEntity entity) {
        return SupportTicket.restore(
                entity.getId(),
                entity.getUserId(),
                entity.getSubject(),
                entity.getMessage(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getRespondedAt()
        );
    }
}
