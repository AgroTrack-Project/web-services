package org.example.agrotrack.farming.domain.model.queries;

/**
 * Requests the list of crops, optionally filtered by plot. A {@code null} {@code plotId}
 * means "all crops"; there is no pagination.
 */
public record ListCropsQuery(
        String plotId
) {
}
