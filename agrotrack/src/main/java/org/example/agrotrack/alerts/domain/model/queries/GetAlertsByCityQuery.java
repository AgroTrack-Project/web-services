package org.example.agrotrack.alerts.domain.model.queries;

/**
 * Query used to request weather alerts for a specific city.
 *
 * @param city the name of the city for which alerts are requested
 */
public record GetAlertsByCityQuery(String city) {}