package org.example.agrotrack.farming.infrastructure.assemblers;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.infrastructure.entities.CropPersistenceEntity;

/**
 * Static mapper between the {@code Crop} domain aggregate and {@code CropPersistenceEntity}.
 * Kept free of Spring wiring so it can be used as plain static methods from the repository
 * adapter.
 */
public final class CropPersistenceAssembler {

    private CropPersistenceAssembler() {
    }

    public static Crop toDomainFromPersistence(CropPersistenceEntity entity) {
        return Crop.restore(
                entity.getId(),
                entity.getPlotId(),
                entity.getType(),
                entity.getSowingDate(),
                entity.getHarvestDate(),
                entity.getStatus()
        );
    }

    public static CropPersistenceEntity toPersistenceFromDomain(Crop crop) {
        CropPersistenceEntity entity = new CropPersistenceEntity();
        copyToPersistenceFromDomain(crop, entity);
        return entity;
    }

    /**
     * Copies the mutable domain fields onto an existing entity instance, used both for building
     * a fresh entity and for updating one already tracked by the persistence context.
     */
    public static void copyToPersistenceFromDomain(Crop crop, CropPersistenceEntity entity) {
        entity.setPlotId(crop.getPlotId());
        entity.setType(crop.getType());
        entity.setSowingDate(crop.getSowingDate());
        entity.setHarvestDate(crop.getHarvestDate());
        entity.setStatus(crop.getStatus());
    }
}
