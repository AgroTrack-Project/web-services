package org.example.agrotrack.shared.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class AuditableAbstractPersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

}
