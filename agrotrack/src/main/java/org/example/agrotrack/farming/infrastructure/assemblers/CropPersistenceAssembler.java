package org.example.agrotrack.farming.infrastructure.assemblers;

import org.example.agrotrack.farming.domain.model.aggregates.Crop;
import org.example.agrotrack.farming.infrastructure.entities.CropPersistenceEntity;

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

    public static void copyToPersistenceFromDomain(Crop crop, CropPersistenceEntity entity) {
        entity.setPlotId(crop.getPlotId());
        entity.setType(crop.getType());
        entity.setSowingDate(crop.getSowingDate());
        entity.setHarvestDate(crop.getHarvestDate());
        entity.setStatus(crop.getStatus());
    }
}
