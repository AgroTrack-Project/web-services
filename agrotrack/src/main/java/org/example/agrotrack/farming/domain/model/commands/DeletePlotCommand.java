package org.example.agrotrack.farming.domain.model.commands;

/**
 * Requests a hard delete of the plot identified by {@code id}. There is no soft-delete
 * or status-based archival for plots; the row is removed outright.
 */
public record DeletePlotCommand(
        String id
) {
}
