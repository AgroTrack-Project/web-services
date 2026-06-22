package org.example.agrotrack.support.interfaces.rest;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.example.agrotrack.support.application.commandservices.SupportTicketCommandService;
import org.example.agrotrack.support.application.queryservices.SupportTicketQueryService;
import org.example.agrotrack.support.domain.model.queries.GetSupportTicketByIdQuery;
import org.example.agrotrack.support.interfaces.rest.resource.CloseSupportTicketResource;
import org.example.agrotrack.support.interfaces.rest.resource.CreateSupportTicketResource;
import org.example.agrotrack.support.interfaces.rest.transform.CloseSupportTicketCommandFromResourceAssembler;
import org.example.agrotrack.support.interfaces.rest.transform.CreateSupportTicketCommandFromResourceAssembler;
import org.example.agrotrack.support.interfaces.rest.transform.ListSupportTicketsQueryFromRequestAssembler;
import org.example.agrotrack.support.interfaces.rest.transform.SupportTicketResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/support_tickets")
@RequiredArgsConstructor
public class SupportTicketsController {

    private final SupportTicketQueryService supportTicketQueryService;
    private final SupportTicketCommandService supportTicketCommandService;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "user_id", required = false) String userId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                supportTicketQueryService.handle(
                        ListSupportTicketsQueryFromRequestAssembler.toQueryFromRequest(userId)
                ),
                tickets -> tickets.stream().map(SupportTicketResourceAssembler::toResource).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                supportTicketQueryService.handle(new GetSupportTicketByIdQuery(id)),
                SupportTicketResourceAssembler::toResource,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateSupportTicketResource request) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                supportTicketCommandService.handle(
                        CreateSupportTicketCommandFromResourceAssembler.toCommandFromResource(request)
                ),
                SupportTicketResourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> close(
            @PathVariable String id,
            @RequestBody(required = false) CloseSupportTicketResource request
    ) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                supportTicketCommandService.handle(
                        CloseSupportTicketCommandFromResourceAssembler.toCommandFromResource(id, request)
                ),
                SupportTicketResourceAssembler::toResource,
                HttpStatus.OK
        );
    }
}
