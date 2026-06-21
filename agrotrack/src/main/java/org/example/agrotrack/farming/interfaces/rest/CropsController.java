package org.example.agrotrack.farming.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.agrotrack.farming.application.commandservices.CropCommandService;
import org.example.agrotrack.farming.application.queryservices.CropQueryService;
import org.example.agrotrack.farming.domain.model.commands.DeleteCropCommand;
import org.example.agrotrack.farming.interfaces.rest.resource.CreateCropResource;
import org.example.agrotrack.farming.interfaces.rest.resource.UpdateCropResource;
import org.example.agrotrack.farming.interfaces.rest.transform.CreateCropCommandFromResourceAssembler;
import org.example.agrotrack.farming.interfaces.rest.transform.CropResourceAssembler;
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

@RestController
@RequestMapping("/crops")
@RequiredArgsConstructor
public class CropsController {

    private final CropQueryService cropQueryService;
    private final CropCommandService cropCommandService;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                cropCommandService.handle(new DeleteCropCommand(id)),
                message -> null,
                HttpStatus.NO_CONTENT
        );
    }
}
