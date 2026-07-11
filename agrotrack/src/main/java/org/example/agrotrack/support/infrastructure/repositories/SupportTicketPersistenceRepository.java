package org.example.agrotrack.support.infrastructure.repositories;

import org.example.agrotrack.support.infrastructure.entities.SupportTicketPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportTicketPersistenceRepository extends JpaRepository<SupportTicketPersistenceEntity, String> {

    List<SupportTicketPersistenceEntity> findByUserIdOrderByCreatedAtDesc(String userId);
}
