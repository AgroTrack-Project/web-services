package org.example.agrotrack.identity.domain.model.commands;

public record CreateUserCommand(
        String name,
        String email,
        String password,
        String userType,
        String planType,
        String companyName
) {}
