package org.example.agrotrack.identity.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.agrotrack.identity.application.commandservices.AlertPreferenceCommandService;
import org.example.agrotrack.identity.application.queryservices.AlertPreferenceQueryService;
import org.example.agrotrack.identity.domain.model.queries.ListAlertPreferencesQuery;
import org.example.agrotrack.identity.interfaces.rest.resource.UpdateAlertPreferenceResource;
import org.example.agrotrack.identity.interfaces.rest.transform.AlertPreferenceResourceAssembler;
import org.example.agrotrack.identity.interfaces.rest.transform.UpdateAlertPreferenceCommandFromResourceAssembler;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/alert_preferences", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Alert Preferences", description = "Endpoints for managing alert preferences")
@RequiredArgsConstructor
public class AlertPreferencesController {

    private final AlertPreferenceCommandService alertPreferenceCommandService;
    private final AlertPreferenceQueryService alertPreferenceQueryService;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name = "user_id", required = false) String userId) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                alertPreferenceQueryService.handle(new ListAlertPreferencesQuery(userId)),
                prefs -> prefs.stream().map(AlertPreferenceResourceAssembler::toResource).toList(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateAlertPreferenceResource resource) {
        return ResponseEntityAssembler.toResponseEntityFromResult(
                alertPreferenceCommandService.handle(
                        UpdateAlertPreferenceCommandFromResourceAssembler.toCommandFromResource(id, resource)
                ),
                AlertPreferenceResourceAssembler::toResource,
                HttpStatus.OK
        );
    }
}
