package org.example.agrotrack.support.domain.repositories;

import org.example.agrotrack.support.domain.model.aggregates.SupportTicket;

import java.util.List;
import java.util.Optional;

public interface SupportTicketRepository {

    Optional<SupportTicket> findById(String id);

    List<SupportTicket> findAll();

    List<SupportTicket> findByUserIdOrderByCreatedAtDesc(String userId);

    SupportTicket save(SupportTicket ticket);

    long count();
}
