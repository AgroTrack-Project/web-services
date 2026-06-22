package org.example.agrotrack.alerts.interfaces.rest.transform;

import org.example.agrotrack.alerts.domain.model.aggregates.Alert;
import org.example.agrotrack.alerts.interfaces.rest.resources.AlertResource;

public final class AlertResourceAssembler {

    private AlertResourceAssembler() {}

    public static AlertResource toResource(Alert alert) {
        return new AlertResource(
                alert.getId(),
                alert.getCity(),
                alert.getTitle(),
                alert.getDescription(),
                alert.getUrgency().name(),
                alert.getGeneratedAt()
        );
    }
}
