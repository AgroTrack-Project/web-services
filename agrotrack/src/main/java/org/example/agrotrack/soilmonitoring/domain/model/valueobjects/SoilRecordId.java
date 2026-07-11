package org.example.agrotrack.soilmonitoring.domain.model.valueobjects;

public record SoilRecordId(String value) {

    public SoilRecordId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("soil_record_id is required");
        }

        value = value.trim();
    }
}