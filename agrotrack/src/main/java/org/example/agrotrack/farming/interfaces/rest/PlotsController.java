package org.example.agrotrack.farming.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.agrotrack.farming.application.commandservices.PlotCommandService;
import org.example.agrotrack.farming.application.queryservices.PlotQueryService;
import org.example.agrotrack.farming.domain.model.commands.DeletePlotCommand;
import org.example.agrotrack.farming.interfaces.rest.resource.CreatePlotResource;
import org.example.agrotrack.farming.interfaces.rest.resource.UpdatePlotResource;
import org.example.agrotrack.farming.interfaces.rest.transform.CreatePlotCommandFromResourceAssembler;
import org.example.agrotrack.farming.interfaces.rest.transform.ListPlotsQueryFromRequestAssembler;
import org.example.agrotrack.farming.interfaces.rest.transform.PlotResourceAssembler;
import org.example.agrotrack.farming.interfaces.rest.transform.UpdatePlotCommandFromResourceAssembler;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
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
@RequestMapping("/plots")
@RequiredArgsConstructor
public class PlotsController {

    private final PlotQueryService plotQueryService;
    private final PlotCommandService plotCommandService;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) String userId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                plotQueryService.handle(ListPlotsQueryFromRequestAssembler.toQueryFromRequest(userId)),
                plots -> plots.stream()
                        .map(PlotResourceAssembler::toResource)
                        .toList(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreatePlotResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                plotCommandService.handle(CreatePlotCommandFromResourceAssembler.toCommandFromResource(resource)),
                PlotResourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UpdatePlotResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                plotCommandService.handle(UpdatePlotCommandFromResourceAssembler.toCommandFromResource(id, resource)),
                PlotResourceAssembler::toResource,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                plotCommandService.handle(new DeletePlotCommand(id)),
                message -> null,
                HttpStatus.NO_CONTENT
        );
    }
}
