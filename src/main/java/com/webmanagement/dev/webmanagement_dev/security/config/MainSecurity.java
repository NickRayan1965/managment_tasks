package com.webmanagement.dev.webmanagement_dev.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.webmanagement.dev.webmanagement_dev.security.jwt.JwtAuthenticationEntryPoint;
import com.webmanagement.dev.webmanagement_dev.security.jwt.JwtFilter;
import com.webmanagement.dev.webmanagement_dev.security.repository.SecurityContextRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class MainSecurity {
  private final SecurityContextRepository securityContextRepository;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private static final List<String> UNPROTECTED_PATHS = List.of(
      "/auth/login",
      "/v2/api-docs",
      "/v3/api-docs",
      "/v3/api-docs/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**",
      "/swagger-ui/**"
  );
  public static List<String> getUnprotectedPaths() {
    return UNPROTECTED_PATHS;
  }

  @Bean
  SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtFilter jwtFilter) {
    Customizer<AuthorizeExchangeSpec> authorizeExchangeCustomizer = authorizeExchangeSpec -> {
      authorizeExchangeSpec
          .pathMatchers(UNPROTECTED_PATHS.toArray(String[]::new)).permitAll()
          .anyExchange().authenticated();
    };
    return http
        .csrf(csrfSpec -> csrfSpec.disable())
        .authorizeExchange(
            authorizeExchangeCustomizer
        )
        .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
        .exceptionHandling(
            e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .securityContextRepository(securityContextRepository)
        .formLogin(formLoginSpec -> formLoginSpec.disable())
        .httpBasic(httpBasicSpec -> httpBasicSpec.disable())
        .logout(logoutSpec -> logoutSpec.disable())
        .build();
  }
}
