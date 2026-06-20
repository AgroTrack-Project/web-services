package org.example.agrotrack.identity.domain.model.aggregates;

import org.example.agrotrack.shared.aggregates.AbstractDomainAggregateRoot;

import java.util.Objects;

public class AlertPreference extends AbstractDomainAggregateRoot<AlertPreference> {

    private final String id;
    private final String userId;
    private boolean frostEnabled;
    private boolean droughtEnabled;
    private boolean heavyRainEnabled;

    private AlertPreference(String id, String userId,
                            boolean frostEnabled, boolean droughtEnabled, boolean heavyRainEnabled) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        this.frostEnabled = frostEnabled;
        this.droughtEnabled = droughtEnabled;
        this.heavyRainEnabled = heavyRainEnabled;
    }

    public static AlertPreference createDefault(String userId) {
        return new AlertPreference(null, userId, true, true, true);
    }

    public static AlertPreference restore(String id, String userId,
                                          boolean frostEnabled, boolean droughtEnabled, boolean heavyRainEnabled) {
        return new AlertPreference(id, userId, frostEnabled, droughtEnabled, heavyRainEnabled);
    }

    public void update(boolean frostEnabled, boolean droughtEnabled, boolean heavyRainEnabled) {
        this.frostEnabled = frostEnabled;
        this.droughtEnabled = droughtEnabled;
        this.heavyRainEnabled = heavyRainEnabled;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public boolean isFrostEnabled() { return frostEnabled; }
    public boolean isDroughtEnabled() { return droughtEnabled; }
    public boolean isHeavyRainEnabled() { return heavyRainEnabled; }
}
