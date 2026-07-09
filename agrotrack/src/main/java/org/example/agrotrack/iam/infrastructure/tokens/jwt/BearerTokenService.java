package org.example.agrotrack.iam.infrastructure.tokens.jwt;

import org.example.agrotrack.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;

public interface BearerTokenService extends TokenService {

    String getBearerTokenFrom(HttpServletRequest request);
}
