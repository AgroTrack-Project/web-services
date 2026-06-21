package org.example.agrotrack.alerts.domain.model.aggregates;

import org.example.agrotrack.alerts.domain.model.valueobjects.AlertUrgency;

import java.time.Instant;
import java.util.UUID;

public class Alert {

    private final String id;
    private final String city;
    private final String title;
    private final String description;
    private final AlertUrgency urgency;
    private final Instant generatedAt;

    public Alert(String city, String title, String description, AlertUrgency urgency) {
        this.id = UUID.randomUUID().toString();
        this.city = city;
        this.title = title;
        this.description = description;
        this.urgency = urgency;
        this.generatedAt = Instant.now();
    }

    public String getId() { return id; }
    public String getCity() { return city; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public AlertUrgency getUrgency() { return urgency; }
    public Instant getGeneratedAt() { return generatedAt; }
}
