package org.example.agrotrack.support.infrastructure.persistence.jpa.repositories;

import org.example.agrotrack.support.infrastructure.persistence.jpa.entities.SupportTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSupportTicketRepository extends JpaRepository<SupportTicketEntity, String> {

    List<SupportTicketEntity> findByUserIdOrderByCreatedAtDesc(String userId);
}
