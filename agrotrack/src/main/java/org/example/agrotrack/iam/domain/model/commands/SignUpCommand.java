package org.example.agrotrack.iam.domain.model.commands;

import org.example.agrotrack.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String email, String password, List<Role> roles) {}
