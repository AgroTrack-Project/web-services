package org.example.agrotrack.iam.infrastructure.authorization.sfs.configuration;

import org.example.agrotrack.iam.infrastructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import org.example.agrotrack.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import org.example.agrotrack.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final BearerTokenService tokenService;
    private final BCryptHashingService hashingService;
    private final AuthenticationEntryPoint unauthorizedRequestHandler;

    public WebSecurityConfiguration(
            @Qualifier("defaultUserDetailsService") UserDetailsService userDetailsService,
            BearerTokenService tokenService,
            BCryptHashingService hashingService,
            AuthenticationEntryPoint authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
        this.unauthorizedRequestHandler = authenticationEntryPoint;
    }

    @Bean
    public BearerAuthorizationRequestFilter authorizationRequestFilter() {
        return new BearerAuthorizationRequestFilter(tokenService, userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(hashingService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return hashingService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedRequestHandler))
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/users/**").authenticated()
                        .anyRequest().permitAll());
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authorizationRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
