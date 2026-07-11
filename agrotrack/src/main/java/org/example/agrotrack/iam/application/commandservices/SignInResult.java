package org.example.agrotrack.iam.application.commandservices;

import org.example.agrotrack.iam.domain.model.aggregates.User;

public record SignInResult(User user, String token) {}
