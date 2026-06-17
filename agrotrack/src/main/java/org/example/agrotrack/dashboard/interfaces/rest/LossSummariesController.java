package org.example.agrotrack.dashboard.interfaces.rest;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.example.agrotrack.dashboard.application.queries.GetLossSummaryByIdQueryService;
import org.example.agrotrack.dashboard.application.queries.ListLossSummariesQueryService;
import org.example.agrotrack.dashboard.interfaces.transform.LossSummaryResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loss_summaries")
@RequiredArgsConstructor
public class LossSummariesController {

    private final ListLossSummariesQueryService listLossSummariesQueryService;
    private final GetLossSummaryByIdQueryService getLossSummaryByIdQueryService;
    private final LossSummaryResourceAssembler resourceAssembler;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "user_id", required = false) String userId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                listLossSummariesQueryService.findAll(userId),
                summaries -> summaries.stream().map(resourceAssembler::toResource).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                getLossSummaryByIdQueryService.findById(id),
                resourceAssembler::toResource,
                HttpStatus.OK
        );
    }
}
