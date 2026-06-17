package org.example.agrotrack.support.infrastructure.adapters;

import org.example.agrotrack.support.application.ports.UserReferencePort;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "agrotrack.integration.identity.mode", havingValue = "stub", matchIfMissing = true)
public class StubUserReferenceAdapter implements UserReferencePort {

    @Override
    public boolean userExists(String userId) {
        return userId != null && !userId.isBlank();
    }
}
