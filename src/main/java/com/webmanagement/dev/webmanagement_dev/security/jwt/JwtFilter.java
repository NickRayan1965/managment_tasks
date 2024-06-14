package com.webmanagement.dev.webmanagement_dev.security.jwt;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class JwtFilter implements WebFilter {
  
  @Override
  @NonNull
  public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    String jwt = getJwtFromRequest(request);
    if (jwt == null) {
      return chain.filter(exchange);
    } 
    exchange.getAttributes().put("jwt", jwt);
    return chain.filter(exchange);
  }
  private String getJwtFromRequest(ServerHttpRequest request) {
    String bearerToken = request.getHeaders().getFirst("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
