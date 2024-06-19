package com.webmanagement.dev.webmanagement_dev.models;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Error {
  public static Error fromRuntimeException(RuntimeException e) {
    return Error.builder()
      .message(e.getMessage())
      .error(e.getClass().getSimpleName())
      .status(500)
      .date(new Date())
      .build();
  }
  public static Error fromRuntimeException(RuntimeException e, HttpStatus status) {
    return Error.builder()
      .message(e.getMessage())
      .error(e.getClass().getSimpleName())
      .status(status.value())
      .date(new Date())
      .build();
  }

  private Object message;
  private String error;
  private Integer status;
  private Date date;
}
