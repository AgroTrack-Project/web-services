package org.example.agrotrack.support.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.support.domain.model.TicketStatus;
import org.example.agrotrack.support.infrastructure.entities.SupportTicketPersistenceEntity;
import org.example.agrotrack.support.infrastructure.repositories.SupportTicketPersistenceRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class SupportDataSeeder implements ApplicationRunner {

    private static final String DEMO_USER_ID = "1";

    private final SupportTicketPersistenceRepository repository;

    @Override
    public void run(ApplicationArguments args) {
        if (repository.count() > 0) {
            return;
        }

        repository.save(openTicket(
                "Problema con riego automático",
                "El sistema no activa el riego según el calendario configurado en la parcela norte.",
                DEMO_USER_ID
        ));
        repository.save(inProgressTicket(
                "Error al exportar PDF del dashboard",
                "Al pulsar exportar PDF la descarga no inicia en Chrome.",
                DEMO_USER_ID
        ));
        repository.save(closedTicket(
                "Consulta sobre plan Enterprise",
                "Necesito información sobre soporte prioritario y límites de parcelas.",
                DEMO_USER_ID,
                Instant.now().minusSeconds(86_400)
        ));
    }

    private SupportTicketPersistenceEntity openTicket(String subject, String message, String userId) {
        SupportTicketPersistenceEntity entity = new SupportTicketPersistenceEntity();
        entity.setUserId(userId);
        entity.setSubject(subject);
        entity.setMessage(message);
        entity.setStatus(TicketStatus.OPEN);
        entity.setCreatedAt(Instant.now().minusSeconds(7_200));
        entity.setRespondedAt(null);
        return entity;
    }

    private SupportTicketPersistenceEntity inProgressTicket(String subject, String message, String userId) {
        SupportTicketPersistenceEntity entity = openTicket(subject, message, userId);
        entity.setStatus(TicketStatus.IN_PROGRESS);
        entity.setCreatedAt(Instant.now().minusSeconds(14_400));
        return entity;
    }

    private SupportTicketPersistenceEntity closedTicket(
            String subject,
            String message,
            String userId,
            Instant respondedAt
    ) {
        SupportTicketPersistenceEntity entity = openTicket(subject, message, userId);
        entity.setStatus(TicketStatus.CLOSED);
        entity.setCreatedAt(respondedAt.minusSeconds(172_800));
        entity.setRespondedAt(respondedAt);
        return entity;
    }
}
