package org.example.agrotrack.farming.infrastructure.assemblers;

import org.example.agrotrack.farming.domain.model.aggregates.Plot;
import org.example.agrotrack.farming.infrastructure.entities.PlotPersistenceEntity;

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

    public static void copyToPersistenceFromDomain(Plot plot, PlotPersistenceEntity entity) {
        entity.setUserId(plot.getUserId());
        entity.setName(plot.getName());
        entity.setLocation(plot.getLocation());
        entity.setSizeHectares(plot.getSizeHectares());
        entity.setStatus(plot.getStatus());
        entity.setCreatedAt(plot.getCreatedAt());
    }
}
