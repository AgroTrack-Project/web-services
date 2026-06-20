package org.example.agrotrack.soilmonitoring.domain.model.aggregates;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.SoilStatus;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.Humidity;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.PlotId;
import org.example.agrotrack.soilmonitoring.domain.model.valueobjects.Temperature;

import java.time.Instant;
import java.util.Objects;

public class SoilRecord extends AbstractDomainAggregateRoot<SoilRecord> {

    private final String id;
    private final PlotId plotId;
    private final Humidity humidity;
    private final Temperature temperature;
    private final Instant recordedAt;

    private SoilRecord(
            String id,
            PlotId plotId,
            Humidity humidity,
            Temperature temperature,
            Instant recordedAt
    ) {
        this.id = id;
        this.plotId = Objects.requireNonNull(plotId, "plotId must not be null");
        this.humidity = Objects.requireNonNull(humidity, "humidity must not be null");
        this.temperature = Objects.requireNonNull(temperature, "temperature must not be null");
        this.recordedAt = Objects.requireNonNull(recordedAt, "recordedAt must not be null");
    }

    public static SoilRecord create(
            PlotId plotId,
            Humidity humidity,
            Temperature temperature,
            Instant recordedAt
    ) {
        return new SoilRecord(null, plotId, humidity, temperature, recordedAt);
    }

    public static SoilRecord restore(
            String id,
            PlotId plotId,
            Humidity humidity,
            Temperature temperature,
            Instant recordedAt
    ) {
        return new SoilRecord(id, plotId, humidity, temperature, recordedAt);
    }

    public SoilStatus getStatus() {
        double humidityValue = humidity.value();

        if (humidityValue < 40) {
            return SoilStatus.DRY;
        }

        if (humidityValue <= 70) {
            return SoilStatus.OPTIMAL;
        }

        return SoilStatus.WET;
    }

    public String getId() {
        return id;
    }

    public PlotId getPlotId() {
        return plotId;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Instant getRecordedAt() {
        return recordedAt;
    }
}