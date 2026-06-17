package org.example.agrotrack.dashboard.interfaces.rest;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.example.agrotrack.dashboard.application.queries.GetWaterConsumptionByIdQueryService;
import org.example.agrotrack.dashboard.application.queries.ListWaterConsumptionsQueryService;
import org.example.agrotrack.dashboard.interfaces.transform.WaterConsumptionResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/water_consumptions")
@RequiredArgsConstructor
public class WaterConsumptionsController {

    private final ListWaterConsumptionsQueryService listWaterConsumptionsQueryService;
    private final GetWaterConsumptionByIdQueryService getWaterConsumptionByIdQueryService;
    private final WaterConsumptionResourceAssembler resourceAssembler;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "user_id", required = false) String userId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                listWaterConsumptionsQueryService.findAll(userId),
                consumptions -> consumptions.stream().map(resourceAssembler::toResource).toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                getWaterConsumptionByIdQueryService.findById(id),
                resourceAssembler::toResource,
                HttpStatus.OK
        );
    }
}
