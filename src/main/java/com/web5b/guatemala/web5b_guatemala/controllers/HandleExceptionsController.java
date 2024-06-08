package com.web5b.guatemala.web5b_guatemala.controllers;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import com.web5b.guatemala.web5b_guatemala.models.Error;
import com.web5b.guatemala.web5b_guatemala.models.NotFoundException;

@RestControllerAdvice
public class HandleExceptionsController {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Error handleException(Exception e) {
    return Error.fromRuntimeException(e);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Error handleUsernameNotFoundException(UsernameNotFoundException e) {
    return Error.fromRuntimeException(e, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error handleNoResourceFoundException(NoResourceFoundException e) {
    return Error.fromRuntimeException(e, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateKeyException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public Error handleDuplicateKeyException(DuplicateKeyException e) {
    return Error.fromRuntimeException(e, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error handleNotFoundException(NotFoundException e) {
    return Error.fromRuntimeException(e, HttpStatus.NOT_FOUND);
  }
}
