package org.example.agrotrack.dashboard.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.example.agrotrack.dashboard.application.commandservices.LossSummaryCommandService;
import org.example.agrotrack.dashboard.application.queryservices.LossSummaryQueryService;
import org.example.agrotrack.dashboard.domain.model.queries.GetLossSummaryByIdQuery;
import org.example.agrotrack.dashboard.interfaces.rest.resource.CreateLossSummaryResource;
import org.example.agrotrack.dashboard.interfaces.rest.transform.CreateLossSummaryCommandFromResourceAssembler;
import org.example.agrotrack.dashboard.interfaces.rest.transform.ListLossSummariesQueryFromRequestAssembler;
import org.example.agrotrack.dashboard.interfaces.rest.transform.LossSummaryResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loss_summaries")
@RequiredArgsConstructor
public class LossSummariesController {

    private final LossSummaryQueryService lossSummaryQueryService;
    private final LossSummaryCommandService lossSummaryCommandService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateLossSummaryResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                lossSummaryCommandService.handle(CreateLossSummaryCommandFromResourceAssembler.toCommandFromResource(resource)),
                LossSummaryResourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "user_id", required = false) String userId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                lossSummaryQueryService.handle(
                        ListLossSummariesQueryFromRequestAssembler.toQueryFromRequest(userId)
                ),
                summaries -> summaries.stream().map(LossSummaryResourceAssembler::toResource).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                lossSummaryQueryService.handle(new GetLossSummaryByIdQuery(id)),
                LossSummaryResourceAssembler::toResource,
                HttpStatus.OK
        );
    }
}
