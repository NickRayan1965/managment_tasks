package com.web5b.guatemala.web5b_guatemala.security.repository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.web5b.guatemala.web5b_guatemala.security.config.MainSecurity;
import com.web5b.guatemala.web5b_guatemala.security.jwt.JwtAuthenticationManager;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {
  private final JwtAuthenticationManager jwtAuthenticationManager;

  @Override
  public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
    return Mono.empty();
  }

  @Override
  public Mono<SecurityContext> load(ServerWebExchange exchange) {
    String path = exchange.getRequest().getPath().value();
    boolean isUnprotectedPath = MainSecurity.getUnprotectedPaths().stream().anyMatch(unprotectedPath -> path.matches(unprotectedPath.replace("**", ".*")));
    if (isUnprotectedPath) {
      return Mono.empty();
    }
    System.out.println("jwt: " + exchange.getAttribute("jwt"));
    String jwt = exchange.getAttribute("jwt");
    return jwtAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwt, jwt))
        .map(SecurityContextImpl::new);
  }

}
