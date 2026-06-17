package org.example.agrotrack.support.interfaces.rest;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.example.agrotrack.support.application.commands.CloseSupportTicketCommandService;
import org.example.agrotrack.support.application.commands.CreateSupportTicketCommandService;
import org.example.agrotrack.support.application.queries.GetSupportTicketByIdQueryService;
import org.example.agrotrack.support.application.queries.ListSupportTicketsQueryService;
import org.example.agrotrack.support.interfaces.resources.CloseSupportTicketResource;
import org.example.agrotrack.support.interfaces.resources.CreateSupportTicketResource;
import org.example.agrotrack.support.interfaces.transform.SupportTicketResourceAssembler;
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

    private final ListSupportTicketsQueryService listSupportTicketsQueryService;
    private final GetSupportTicketByIdQueryService getSupportTicketByIdQueryService;
    private final CreateSupportTicketCommandService createSupportTicketCommandService;
    private final CloseSupportTicketCommandService closeSupportTicketCommandService;
    private final SupportTicketResourceAssembler resourceAssembler;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "user_id", required = false) String userId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                listSupportTicketsQueryService.findAll(userId),
                tickets -> tickets.stream().map(resourceAssembler::toResource).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                getSupportTicketByIdQueryService.findById(id),
                resourceAssembler::toResource,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateSupportTicketResource request) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                createSupportTicketCommandService.create(request.userId(), request.subject(), request.message()),
                resourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> close(
            @PathVariable String id,
            @RequestBody(required = false) CloseSupportTicketResource request
    ) {
        String requestedStatus = request != null ? request.status() : null;
        return ResponseEntityAssembler.toResponseEntityFromResult(
                closeSupportTicketCommandService.closeIfRequested(id, requestedStatus),
                resourceAssembler::toResource,
                HttpStatus.OK
        );
    }
}
