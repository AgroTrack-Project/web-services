package org.example.agrotrack.dashboard.interfaces.rest;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.example.agrotrack.dashboard.application.queries.GetYieldSummaryByIdQueryService;
import org.example.agrotrack.dashboard.application.queries.ListYieldSummariesQueryService;
import org.example.agrotrack.dashboard.interfaces.transform.YieldSummaryResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yield_summaries")
@RequiredArgsConstructor
public class YieldSummariesController {

    private final ListYieldSummariesQueryService listYieldSummariesQueryService;
    private final GetYieldSummaryByIdQueryService getYieldSummaryByIdQueryService;
    private final YieldSummaryResourceAssembler resourceAssembler;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "user_id", required = false) String userId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                listYieldSummariesQueryService.findAll(userId),
                summaries -> summaries.stream().map(resourceAssembler::toResource).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                getYieldSummaryByIdQueryService.findById(id),
                resourceAssembler::toResource,
                HttpStatus.OK
        );
    }
}
