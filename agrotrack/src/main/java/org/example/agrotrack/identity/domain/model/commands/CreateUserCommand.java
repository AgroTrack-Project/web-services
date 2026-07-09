package org.example.agrotrack.identity.domain.model.commands;

public record CreateUserCommand(
        String name,
        String email,
        String iamUserId,
        String planType,
        String companyName
) {}
