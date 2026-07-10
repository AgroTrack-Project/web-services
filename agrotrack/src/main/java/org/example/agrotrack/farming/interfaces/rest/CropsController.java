package org.example.agrotrack.farming.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.agrotrack.farming.application.commandservices.CropCommandService;
import org.example.agrotrack.farming.application.queryservices.CropQueryService;
import org.example.agrotrack.farming.domain.model.commands.DeleteCropCommand;
import org.example.agrotrack.farming.interfaces.rest.resource.CreateCropResource;
import org.example.agrotrack.farming.interfaces.rest.resource.HarvestCropResource;
import org.example.agrotrack.farming.interfaces.rest.resource.UpdateCropResource;
import org.example.agrotrack.farming.interfaces.rest.transform.CreateCropCommandFromResourceAssembler;
import org.example.agrotrack.farming.interfaces.rest.transform.CropResourceAssembler;
import org.example.agrotrack.farming.interfaces.rest.transform.HarvestCropCommandFromResourceAssembler;
import org.example.agrotrack.farming.interfaces.rest.transform.ListCropsQueryFromRequestAssembler;
import org.example.agrotrack.farming.interfaces.rest.transform.UpdateCropCommandFromResourceAssembler;
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

/**
 * REST entry point for the {@code Crop} aggregate. There is no {@code GET /crops/{id}} endpoint —
 * only the list and mutation endpoints below are exposed, since the frontend never fetches a
 * single crop by id directly. Harvesting is a dedicated {@code PUT /crops/{id}/harvest} endpoint
 * rather than folded into the general update, since it carries different domain semantics
 * (a one-way status transition, see {@code Crop.harvest()}).
 */
@RestController
@RequestMapping("/crops")
@RequiredArgsConstructor
public class CropsController {

    private final CropQueryService cropQueryService;
    private final CropCommandService cropCommandService;

    /**
     * Lists crops, optionally filtered by {@code plotId} query parameter.
     */
    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) String plotId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                cropQueryService.handle(ListCropsQueryFromRequestAssembler.toQueryFromRequest(plotId)),
                crops -> crops.stream()
                        .map(CropResourceAssembler::toResource)
                        .toList(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateCropResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                cropCommandService.handle(CreateCropCommandFromResourceAssembler.toCommandFromResource(resource)),
                CropResourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UpdateCropResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                cropCommandService.handle(UpdateCropCommandFromResourceAssembler.toCommandFromResource(id, resource)),
                CropResourceAssembler::toResource,
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/harvest")
    public ResponseEntity<?> harvest(@PathVariable String id, @Valid @RequestBody HarvestCropResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                cropCommandService.handle(HarvestCropCommandFromResourceAssembler.toCommandFromResource(id, resource)),
                CropResourceAssembler::toResource,
                HttpStatus.OK
        );
    }

    /**
     * Hard-deletes a crop. The success message from the command is discarded since a
     * {@code 204 No Content} response carries no body.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                cropCommandService.handle(new DeleteCropCommand(id)),
                message -> null,
                HttpStatus.NO_CONTENT
        );
    }
}
