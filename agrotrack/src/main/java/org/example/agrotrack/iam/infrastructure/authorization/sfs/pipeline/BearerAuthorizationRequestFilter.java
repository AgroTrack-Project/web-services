package org.example.agrotrack.iam.infrastructure.authorization.sfs.pipeline;

import org.example.agrotrack.iam.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;
import org.example.agrotrack.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {

    private final BearerTokenService tokenService;

    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;

    public BearerAuthorizationRequestFilter(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = tokenService.getBearerTokenFrom(request);
            if (token != null && tokenService.validateToken(token)) {
                var email = tokenService.getEmailFromToken(token);
                var userDetails = userDetailsService.loadUserByUsername(email);
                SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request));
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
