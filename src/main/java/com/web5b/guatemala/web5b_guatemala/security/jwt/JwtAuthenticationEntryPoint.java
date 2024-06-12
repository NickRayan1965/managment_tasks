package com.web5b.guatemala.web5b_guatemala.security.jwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.web5b.guatemala.web5b_guatemala.security.helpers.DataBufferWriter;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

  private final DataBufferWriter dataBufferWriter;

  @Override
  public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
    System.out.println("JwtAuthenticationEntryPoint.commence()");
    System.out.println(ex.getMessage());
    return Mono.just(ex)
        .map(this::getErrorAttributes)
        .doOnNext(error -> {
          ServerHttpResponse response = exchange.getResponse();
          response.getHeaders().setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
          response.setStatusCode(HttpStatus.UNAUTHORIZED);
        })
        .flatMap(error -> dataBufferWriter.write(exchange.getResponse(), error));
  }
  private Map<String, Object> getErrorAttributes(AuthenticationException ex) {
    Map<String, Object> error = new HashMap<>();
    error.put("error", ex.getMessage());
    error.put("status", HttpStatus.UNAUTHORIZED.value());
    error.put("message", "Unauthorized");
    return error;
  }
  
}
