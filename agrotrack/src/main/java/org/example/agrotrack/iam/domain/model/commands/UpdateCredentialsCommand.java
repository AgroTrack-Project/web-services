package org.example.agrotrack.iam.domain.model.commands;

public record UpdateCredentialsCommand(String userId, String email, String password) {}
