package org.example.agrotrack.dashboard.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.example.agrotrack.dashboard.application.commandservices.WaterConsumptionCommandService;
import org.example.agrotrack.dashboard.application.queryservices.WaterConsumptionQueryService;
import org.example.agrotrack.dashboard.domain.model.queries.GetWaterConsumptionByIdQuery;
import org.example.agrotrack.dashboard.interfaces.rest.resource.CreateWaterConsumptionResource;
import org.example.agrotrack.dashboard.interfaces.rest.transform.CreateWaterConsumptionCommandFromResourceAssembler;
import org.example.agrotrack.dashboard.interfaces.rest.transform.ListWaterConsumptionsQueryFromRequestAssembler;
import org.example.agrotrack.dashboard.interfaces.rest.transform.WaterConsumptionResourceAssembler;
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
@RequestMapping("/water_consumptions")
@RequiredArgsConstructor
public class WaterConsumptionsController {

    private final WaterConsumptionQueryService waterConsumptionQueryService;
    private final WaterConsumptionCommandService waterConsumptionCommandService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateWaterConsumptionResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                waterConsumptionCommandService.handle(CreateWaterConsumptionCommandFromResourceAssembler.toCommandFromResource(resource)),
                WaterConsumptionResourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "user_id", required = false) String userId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                waterConsumptionQueryService.handle(
                        ListWaterConsumptionsQueryFromRequestAssembler.toQueryFromRequest(userId)
                ),
                consumptions -> consumptions.stream().map(WaterConsumptionResourceAssembler::toResource).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                waterConsumptionQueryService.handle(new GetWaterConsumptionByIdQuery(id)),
                WaterConsumptionResourceAssembler::toResource,
                HttpStatus.OK
        );
    }
}
