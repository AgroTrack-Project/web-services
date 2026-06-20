package org.example.agrotrack.soilmonitoring.interfaces.rest;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.interfaces.resources.MessageResource;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.example.agrotrack.soilmonitoring.application.commandservices.IrrigationRecommendationCommandService;
import org.example.agrotrack.soilmonitoring.application.queryservices.IrrigationRecommendationQueryService;
import org.example.agrotrack.soilmonitoring.domain.model.commands.DeleteIrrigationRecommendationCommand;
import org.example.agrotrack.soilmonitoring.domain.model.queries.GetIrrigationRecommendationByIdQuery;
import org.example.agrotrack.soilmonitoring.interfaces.rest.resource.CreateIrrigationRecommendationResource;
import org.example.agrotrack.soilmonitoring.interfaces.rest.resource.UpdateIrrigationRecommendationResource;
import org.example.agrotrack.soilmonitoring.interfaces.rest.transform.CreateIrrigationRecommendationCommandFromResourceAssembler;
import org.example.agrotrack.soilmonitoring.interfaces.rest.transform.IrrigationRecommendationResourceAssembler;
import org.example.agrotrack.soilmonitoring.interfaces.rest.transform.ListIrrigationRecommendationsQueryFromRequestAssembler;
import org.example.agrotrack.soilmonitoring.interfaces.rest.transform.UpdateIrrigationRecommendationCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/irrigation_recommendations")
@RequiredArgsConstructor
public class IrrigationRecommendationsController {

    private final IrrigationRecommendationQueryService queryService;
    private final IrrigationRecommendationCommandService commandService;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "plot_id", required = false) String plotId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                queryService.handle(
                        ListIrrigationRecommendationsQueryFromRequestAssembler.toQueryFromRequest(plotId)
                ),
                recommendations -> recommendations.stream()
                        .map(IrrigationRecommendationResourceAssembler::toResource)
                        .toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                queryService.handle(new GetIrrigationRecommendationByIdQuery(id)),
                IrrigationRecommendationResourceAssembler::toResource,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateIrrigationRecommendationResource request) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                commandService.handle(
                        CreateIrrigationRecommendationCommandFromResourceAssembler.toCommandFromResource(request)
                ),
                IrrigationRecommendationResourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody UpdateIrrigationRecommendationResource request
    ) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                commandService.handle(
                        UpdateIrrigationRecommendationCommandFromResourceAssembler.toCommandFromResource(id, request)
                ),
                IrrigationRecommendationResourceAssembler::toResource,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                commandService.handle(new DeleteIrrigationRecommendationCommand(id)),
                MessageResource::new,
                HttpStatus.OK
        );
    }
}