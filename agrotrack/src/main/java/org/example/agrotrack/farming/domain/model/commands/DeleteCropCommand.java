package org.example.agrotrack.farming.domain.model.commands;

/**
 * Requests a hard delete of the crop identified by {@code id}. There is no soft-delete
 * or status-based archival for crops; the row is removed outright.
 */
public record DeleteCropCommand(
        String id
) {
}
