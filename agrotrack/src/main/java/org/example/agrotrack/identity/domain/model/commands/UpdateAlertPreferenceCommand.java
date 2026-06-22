package org.example.agrotrack.identity.domain.model.commands;

public record UpdateAlertPreferenceCommand(
        String preferenceId,
        boolean frostEnabled,
        boolean droughtEnabled,
        boolean heavyRainEnabled
) {}
