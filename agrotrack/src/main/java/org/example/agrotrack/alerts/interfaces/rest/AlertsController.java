package org.example.agrotrack.alerts.interfaces.rest;

import lombok.RequiredArgsConstructor;
import org.example.agrotrack.alerts.application.queryservices.AlertQueryService;
import org.example.agrotrack.alerts.domain.model.queries.GetAlertsByCityQuery;
import org.example.agrotrack.alerts.interfaces.rest.transform.AlertResourceAssembler;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertsController {

    private final AlertQueryService alertQueryService;

    @GetMapping
    public ResponseEntity<?> listByCity(@RequestParam String city) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                alertQueryService.handle(new GetAlertsByCityQuery(city)),
                alerts -> alerts.stream()
                        .map(AlertResourceAssembler::toResource)
                        .toList(),
                HttpStatus.OK
        );
    }
}
