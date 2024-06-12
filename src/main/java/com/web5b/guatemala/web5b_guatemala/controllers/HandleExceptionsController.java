package com.web5b.guatemala.web5b_guatemala.controllers;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import com.web5b.guatemala.web5b_guatemala.models.Error;
import com.web5b.guatemala.web5b_guatemala.models.ForbiddenException;
import com.web5b.guatemala.web5b_guatemala.models.NotFoundException;

@RestControllerAdvice
public class HandleExceptionsController {

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Error handleException(RuntimeException e) {
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

  @ExceptionHandler({BadCredentialsException.class, DisabledException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Error handleBadCredentialsException(RuntimeException e) {
    return Error.fromRuntimeException(e, HttpStatus.UNAUTHORIZED);
  }
  
  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public Error handleForbiddenException(ForbiddenException e) {
    return Error.fromRuntimeException(e, HttpStatus.FORBIDDEN);
  }
}
