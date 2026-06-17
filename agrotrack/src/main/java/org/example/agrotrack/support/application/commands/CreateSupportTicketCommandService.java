package org.example.agrotrack.support.application.commands;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.support.application.ports.UserReferencePort;
import org.example.agrotrack.support.domain.model.SupportTicket;
import org.example.agrotrack.support.infrastructure.persistence.jpa.repositories.JpaSupportTicketRepository;
import org.example.agrotrack.support.interfaces.transform.SupportTicketEntityAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CreateSupportTicketCommandService {

    private static final int MAX_SUBJECT_LENGTH = 200;
    private static final int MAX_MESSAGE_LENGTH = 5000;

    private final JpaSupportTicketRepository repository;
    private final SupportTicketEntityAssembler entityAssembler;
    private final UserReferencePort userReferencePort;

    @Transactional
    public Result<SupportTicket, ApplicationError> create(String userId, String subject, String message) {
        ApplicationError validationError = validateInput(userId, subject, message);
        if (validationError != null) {
            return Result.failure(validationError);
        }

        if (!userReferencePort.userExists(userId)) {
            return Result.failure(ApplicationError.validationError("user_id", "User does not exist"));
        }

        SupportTicket ticket = SupportTicket.open(
                userId.trim(),
                subject.trim(),
                message.trim(),
                Instant.now()
        );

        var saved = repository.save(entityAssembler.toNewEntity(ticket));
        return Result.success(entityAssembler.toDomain(saved));
    }

    private ApplicationError validateInput(String userId, String subject, String message) {
        if (userId == null || userId.isBlank()) {
            return ApplicationError.validationError("user_id", "user_id is required");
        }
        if (subject == null || subject.isBlank()) {
            return ApplicationError.validationError("subject", "subject is required");
        }
        if (message == null || message.isBlank()) {
            return ApplicationError.validationError("message", "message is required");
        }
        if (subject.trim().length() > MAX_SUBJECT_LENGTH) {
            return ApplicationError.validationError(
                    "subject",
                    "subject must be at most %d characters".formatted(MAX_SUBJECT_LENGTH)
            );
        }
        if (message.trim().length() > MAX_MESSAGE_LENGTH) {
            return ApplicationError.validationError(
                    "message",
                    "message must be at most %d characters".formatted(MAX_MESSAGE_LENGTH)
            );
        }
        return null;
    }
}
