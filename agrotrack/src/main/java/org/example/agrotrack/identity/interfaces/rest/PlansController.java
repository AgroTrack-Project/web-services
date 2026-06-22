package org.example.agrotrack.identity.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.agrotrack.identity.application.queryservices.PlanQueryService;
import org.example.agrotrack.identity.domain.model.queries.ListPlansQuery;
import org.example.agrotrack.identity.interfaces.rest.transform.PlanResourceAssembler;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/plans", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Plans", description = "Endpoints for retrieving subscription plans")
@RequiredArgsConstructor
public class PlansController {

    private final PlanQueryService planQueryService;

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                planQueryService.handle(new ListPlansQuery()),
                plans -> plans.stream().map(PlanResourceAssembler::toResource).toList(),
                HttpStatus.OK
        );
    }
}
