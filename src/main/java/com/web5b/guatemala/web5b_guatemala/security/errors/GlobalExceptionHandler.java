package com.web5b.guatemala.web5b_guatemala.security.errors;

import java.util.Map;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.web5b.guatemala.web5b_guatemala.security.helpers.DataBufferWriter;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

  private final DataBufferWriter bufferWriter;

  @Override
  @NonNull
  public Mono<Void> handle(@NonNull ServerWebExchange exchange, @NonNull Throwable ex) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    String message = ex.getMessage();
    String error = ex.getClass().getSimpleName();

    if (ex instanceof DisabledException) {
      status = HttpStatus.UNAUTHORIZED;
    } else if (ex instanceof UsernameNotFoundException) {
      status = HttpStatus.UNAUTHORIZED;
    } else if (ex instanceof JwtAuthenticationException) {
      status = HttpStatus.UNAUTHORIZED;
    }

    if (exchange.getResponse().isCommitted()) {
      return Mono.error(ex);
    }
    Map<String, Object> appError = Map.of("error", error, "status", status.value(), "message", message);
    exchange.getResponse().setStatusCode(status);
    exchange.getResponse().getHeaders().setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
    return bufferWriter.write(exchange.getResponse(), appError);
  }

}