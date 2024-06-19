package com.webmanagement.dev.webmanagement_dev.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ServerWebInputException;

import com.webmanagement.dev.webmanagement_dev.models.Error;
import com.webmanagement.dev.webmanagement_dev.models.ForbiddenException;
import com.webmanagement.dev.webmanagement_dev.models.NotFoundException;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class HandleExceptionsController {

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Mono<Error> handleException(RuntimeException e) {
    return Mono.just(Error.fromRuntimeException(e));
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Mono<Error> handleUsernameNotFoundException(UsernameNotFoundException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.UNAUTHORIZED));
  }

  @ExceptionHandler(NoResourceFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Mono<Error> handleNoResourceFoundException(NoResourceFoundException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.NOT_FOUND));
  }

  @ExceptionHandler(DuplicateKeyException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public Mono<Error> handleDuplicateKeyException(DuplicateKeyException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.CONFLICT));
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Mono<Error> handleNotFoundException(NotFoundException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.NOT_FOUND));
  }

  @ExceptionHandler({BadCredentialsException.class, DisabledException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Mono<Error> handleBadCredentialsException(RuntimeException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.UNAUTHORIZED));
  }
  
  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public Mono<Error> handleForbiddenException(ForbiddenException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.FORBIDDEN));
  }

  @ExceptionHandler(WebExchangeBindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<Error> handleValidationExceptions(WebExchangeBindException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.add("(" + error.getField() + ") "+ error.getDefaultMessage())
        );
        return Mono.just(Error.builder()
            .message(errors)
            .error("ValidationException")
            .status(HttpStatus.BAD_REQUEST.value())
            .build());
    }
    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<Error> handleValidationExceptions(ServerWebInputException ex) {
        return Mono.just(Error.builder()
            .message("Invalid Body (Check the respective schema)")
            .error("ValidationException")
            .status(HttpStatus.BAD_REQUEST.value())
            .build());
    }
}
