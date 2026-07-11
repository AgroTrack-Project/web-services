package org.example.agrotrack.identity.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.agrotrack.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "alert_preferences")
public class AlertPreferencePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "user_id", nullable = false, unique = true, length = 36)
    private String userId;

    @Column(name = "frost_enabled", nullable = false)
    private boolean frostEnabled;

    @Column(name = "drought_enabled", nullable = false)
    private boolean droughtEnabled;

    @Column(name = "heavy_rain_enabled", nullable = false)
    private boolean heavyRainEnabled;
}
