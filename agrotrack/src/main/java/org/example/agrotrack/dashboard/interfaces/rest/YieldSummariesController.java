package org.example.agrotrack.dashboard.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.example.agrotrack.dashboard.application.commandservices.YieldSummaryCommandService;
import org.example.agrotrack.dashboard.application.queryservices.YieldSummaryQueryService;
import org.example.agrotrack.dashboard.domain.model.queries.GetYieldSummaryByIdQuery;
import org.example.agrotrack.dashboard.interfaces.rest.resource.CreateYieldSummaryResource;
import org.example.agrotrack.dashboard.interfaces.rest.transform.CreateYieldSummaryCommandFromResourceAssembler;
import org.example.agrotrack.dashboard.interfaces.rest.transform.ListYieldSummariesQueryFromRequestAssembler;
import org.example.agrotrack.dashboard.interfaces.rest.transform.YieldSummaryResourceAssembler;
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
@RequestMapping("/yield_summaries")
@RequiredArgsConstructor
public class YieldSummariesController {

    private final YieldSummaryQueryService yieldSummaryQueryService;
    private final YieldSummaryCommandService yieldSummaryCommandService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateYieldSummaryResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                yieldSummaryCommandService.handle(CreateYieldSummaryCommandFromResourceAssembler.toCommandFromResource(resource)),
                YieldSummaryResourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "user_id", required = false) String userId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                yieldSummaryQueryService.handle(
                        ListYieldSummariesQueryFromRequestAssembler.toQueryFromRequest(userId)
                ),
                summaries -> summaries.stream().map(YieldSummaryResourceAssembler::toResource).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                yieldSummaryQueryService.handle(new GetYieldSummaryByIdQuery(id)),
                YieldSummaryResourceAssembler::toResource,
                HttpStatus.OK
        );
    }
}
