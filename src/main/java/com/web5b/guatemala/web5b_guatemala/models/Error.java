package com.web5b.guatemala.web5b_guatemala.models;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
  public static Error fromRuntimeException(Exception e) {
    Error error = new Error();
    error.setMessage(e.getMessage());
    error.setError(e.getClass().getSimpleName());
    error.setStatus(500);
    error.setDate(new Date());
    return error;
  }
  public static Error fromRuntimeException(RuntimeException e, HttpStatus status) {
    Error error = new Error();
    error.setMessage(e.getMessage());
    error.setError(e.getClass().getSimpleName());
    error.setStatus(status.value());
    error.setDate(new Date());
    return error;
  }

  private String message;
  private String error;
  private Integer status;
  private Date date;
}
