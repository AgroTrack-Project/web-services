package org.example.agrotrack.soilmonitoring.domain.model.aggregates;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;
import org.example.agrotrack.soilmonitoring.domain.model.IrrigationRecommendationStatus;
import org.example.agrotrack.soilmonitoring.domain.model.IrrigationUrgency;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.PlotId;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.SoilRecordId;

import java.time.Instant;
import java.util.Objects;

public class IrrigationRecommendation extends AbstractDomainAggregateRoot<IrrigationRecommendation> {

    private final String id;
    private final PlotId plotId;
    private final SoilRecordId soilRecordId;
    private final String message;
    private final IrrigationUrgency urgency;
    private IrrigationRecommendationStatus status;
    private final Instant generatedAt;
    private Instant respondedAt;

    private IrrigationRecommendation(
            String id,
            PlotId plotId,
            SoilRecordId soilRecordId,
            String message,
            IrrigationUrgency urgency,
            IrrigationRecommendationStatus status,
            Instant generatedAt,
            Instant respondedAt
    ) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message is required");
        }

        this.id = id;
        this.plotId = Objects.requireNonNull(plotId, "plotId must not be null");
        this.soilRecordId = Objects.requireNonNull(soilRecordId, "soilRecordId must not be null");
        this.message = message.trim();
        this.urgency = Objects.requireNonNull(urgency, "urgency must not be null");
        this.status = Objects.requireNonNull(status, "status must not be null");
        this.generatedAt = Objects.requireNonNull(generatedAt, "generatedAt must not be null");
        this.respondedAt = respondedAt;
    }

    public static IrrigationRecommendation create(
            PlotId plotId,
            SoilRecordId soilRecordId,
            String message,
            IrrigationUrgency urgency,
            IrrigationRecommendationStatus status,
            Instant generatedAt,
            Instant respondedAt
    ) {
        return new IrrigationRecommendation(
                null,
                plotId,
                soilRecordId,
                message,
                urgency,
                status,
                generatedAt,
                respondedAt
        );
    }

    public static IrrigationRecommendation restore(
            String id,
            PlotId plotId,
            SoilRecordId soilRecordId,
            String message,
            IrrigationUrgency urgency,
            IrrigationRecommendationStatus status,
            Instant generatedAt,
            Instant respondedAt
    ) {
        return new IrrigationRecommendation(
                id,
                plotId,
                soilRecordId,
                message,
                urgency,
                status,
                generatedAt,
                respondedAt
        );
    }

    public void updateStatus(IrrigationRecommendationStatus status, Instant respondedAt) {
        this.status = Objects.requireNonNull(status, "status must not be null");

        if (status == IrrigationRecommendationStatus.PENDING) {
            this.respondedAt = null;
            return;
        }

        this.respondedAt = respondedAt != null ? respondedAt : Instant.now();
    }

    public String getId() {
        return id;
    }

    public PlotId getPlotId() {
        return plotId;
    }

    public SoilRecordId getSoilRecordId() {
        return soilRecordId;
    }

    public String getMessage() {
        return message;
    }

    public IrrigationUrgency getUrgency() {
        return urgency;
    }

    public IrrigationRecommendationStatus getStatus() {
        return status;
    }

    public Instant getGeneratedAt() {
        return generatedAt;
    }

    public Instant getRespondedAt() {
        return respondedAt;
    }
}