package org.example.agrotrack.soilmonitoring.interfaces.rest;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.shared.interfaces.resources.MessageResource;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.example.agrotrack.soilmonitoring.application.commandservices.SoilRecordCommandService;
import org.example.agrotrack.soilmonitoring.application.queryservices.SoilRecordQueryService;
import org.example.agrotrack.soilmonitoring.domain.model.commands.DeleteSoilRecordCommand;
import org.example.agrotrack.soilmonitoring.domain.model.queries.GetSoilRecordByIdQuery;
import org.example.agrotrack.soilmonitoring.interfaces.rest.resource.CreateSoilRecordResource;
import org.example.agrotrack.soilmonitoring.interfaces.rest.transform.CreateSoilRecordCommandFromResourceAssembler;
import org.example.agrotrack.soilmonitoring.interfaces.rest.transform.ListSoilRecordsQueryFromRequestAssembler;
import org.example.agrotrack.soilmonitoring.interfaces.rest.transform.SoilRecordResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/soil_records")
@RequiredArgsConstructor
public class SoilRecordsController {

    private final SoilRecordQueryService soilRecordQueryService;
    private final SoilRecordCommandService soilRecordCommandService;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "plot_id", required = false) String plotId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                soilRecordQueryService.handle(
                        ListSoilRecordsQueryFromRequestAssembler.toQueryFromRequest(plotId)
                ),
                soilRecords -> soilRecords.stream()
                        .map(SoilRecordResourceAssembler::toResource)
                        .toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                soilRecordQueryService.handle(new GetSoilRecordByIdQuery(id)),
                SoilRecordResourceAssembler::toResource,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateSoilRecordResource request) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                soilRecordCommandService.handle(
                        CreateSoilRecordCommandFromResourceAssembler.toCommandFromResource(request)
                ),
                SoilRecordResourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                soilRecordCommandService.handle(new DeleteSoilRecordCommand(id)),
                MessageResource::new,
                HttpStatus.OK
        );
    }
}