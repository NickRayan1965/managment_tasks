package com.web5b.guatemala.web5b_guatemala.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.web5b.guatemala.web5b_guatemala.security.jwt.JwtAuthenticationEntryPoint;
import com.web5b.guatemala.web5b_guatemala.security.jwt.JwtFilter;
import com.web5b.guatemala.web5b_guatemala.security.repository.SecurityContextRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class MainSecurity {
  private final SecurityContextRepository securityContextRepository;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  @Bean
  SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtFilter jwtFilter) {
    return http
        .csrf(csrfSpec -> csrfSpec.disable())
        .authorizeExchange(
            authorizeExchangeSpec -> authorizeExchangeSpec
                .pathMatchers("/auth/**").permitAll()
                .anyExchange().authenticated()
                
        )
        .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
        .exceptionHandling(
          e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint)
        )
        .securityContextRepository(securityContextRepository)
        .formLogin(formLoginSpec -> formLoginSpec.disable())
        .httpBasic(httpBasicSpec -> httpBasicSpec.disable())
        .logout(logoutSpec -> logoutSpec.disable())
        .build();
  }
}
