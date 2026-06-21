package org.example.agrotrack.alerts.domain.model.aggregates;

import org.example.agrotrack.alerts.domain.model.valueobjects.AlertUrgency;

import java.time.Instant;
import java.util.UUID;

/**
 * Represents a weather alert generated for a specific city.
 * Contains alert details, urgency level, and generation timestamp.
 */
public class Alert {

    private final String id;
    private final String city;
    private final String title;
    private final String description;
    private final AlertUrgency urgency;
    private final Instant generatedAt;

    /**
     * Creates a new alert instance.
     *
     * @param city the city associated with the alert
     * @param title the alert title
     * @param description the alert description
     * @param urgency the urgency level of the alert
     */
    public Alert(String city, String title, String description, AlertUrgency urgency) {
        this.id = UUID.randomUUID().toString();
        this.city = city;
        this.title = title;
        this.description = description;
        this.urgency = urgency;
        this.generatedAt = Instant.now();
    }

    /**
     * @return the unique identifier of the alert
     */
    public String getId() {
        return id;
    }

    /**
     * @return the city associated with the alert
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the alert title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the alert description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the alert urgency level
     */
    public AlertUrgency getUrgency() {
        return urgency;
    }

    /**
     * @return the timestamp when the alert was generated
     */
    public Instant getGeneratedAt() {
        return generatedAt;
    }
}