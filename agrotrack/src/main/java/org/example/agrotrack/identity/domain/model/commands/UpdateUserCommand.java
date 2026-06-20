package org.example.agrotrack.identity.domain.model.commands;

public record UpdateUserCommand(
        String userId,
        String name,
        String email,
        String password,
        String planType,
        String companyName
) {}
