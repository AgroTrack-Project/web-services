package org.example.agrotrack.iam.infrastructure.hashing.bcrypt;

import org.example.agrotrack.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
