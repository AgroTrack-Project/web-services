package org.example.agrotrack.farming.infrastructure.assemblers;

import org.example.agrotrack.farming.domain.model.aggregates.Plot;
import org.example.agrotrack.farming.infrastructure.entities.PlotPersistenceEntity;

/**
 * Static mapper between the {@code Plot} domain aggregate and {@code PlotPersistenceEntity}.
 * Kept free of Spring wiring so it can be used as plain static methods from the repository
 * adapter.
 */
public final class PlotPersistenceAssembler {

    private PlotPersistenceAssembler() {
    }

    public static Plot toDomainFromPersistence(PlotPersistenceEntity entity) {
        return Plot.restore(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getLocation(),
                entity.getSizeHectares(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }

    public static PlotPersistenceEntity toPersistenceFromDomain(Plot plot) {
        PlotPersistenceEntity entity = new PlotPersistenceEntity();
        copyToPersistenceFromDomain(plot, entity);
        return entity;
    }

    /**
     * Copies the mutable domain fields onto an existing entity instance, used both for building
     * a fresh entity and for updating one already tracked by the persistence context.
     */
    public static void copyToPersistenceFromDomain(Plot plot, PlotPersistenceEntity entity) {
        entity.setUserId(plot.getUserId());
        entity.setName(plot.getName());
        entity.setLocation(plot.getLocation());
        entity.setSizeHectares(plot.getSizeHectares());
        entity.setStatus(plot.getStatus());
        entity.setCreatedAt(plot.getCreatedAt());
    }
}
