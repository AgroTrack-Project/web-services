package org.example.agrotrack.support.application.queryservices;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.example.agrotrack.support.domain.model.aggregates.SupportTicket;
import org.example.agrotrack.support.domain.model.queries.GetSupportTicketByIdQuery;
import org.example.agrotrack.support.domain.model.queries.ListSupportTicketsQuery;

import java.util.List;

public interface SupportTicketQueryService {

    Result<List<SupportTicket>, ApplicationError> handle(ListSupportTicketsQuery query);

    Result<SupportTicket, ApplicationError> handle(GetSupportTicketByIdQuery query);
}
