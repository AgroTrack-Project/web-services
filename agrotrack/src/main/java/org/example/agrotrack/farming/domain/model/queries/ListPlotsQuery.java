package org.example.agrotrack.farming.domain.model.queries;

/**
 * Requests the list of plots, optionally filtered by owning user. A {@code null} or blank
 * {@code userId} means "all plots"; there is no pagination.
 */
public record ListPlotsQuery(
        String userId
) {
}
