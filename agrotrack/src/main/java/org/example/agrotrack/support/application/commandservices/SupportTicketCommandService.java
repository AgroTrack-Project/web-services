package org.example.agrotrack.support.application.commandservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.support.domain.model.aggregates.SupportTicket;
import org.example.agrotrack.support.domain.model.commands.CloseSupportTicketCommand;
import org.example.agrotrack.support.domain.model.commands.CreateSupportTicketCommand;

public interface SupportTicketCommandService {

    Result<SupportTicket, ApplicationError> handle(CreateSupportTicketCommand command);

    Result<SupportTicket, ApplicationError> handle(CloseSupportTicketCommand command);
}
