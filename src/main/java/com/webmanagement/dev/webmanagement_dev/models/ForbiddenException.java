package com.webmanagement.dev.webmanagement_dev.models;

public class ForbiddenException extends RuntimeException {
  private static final long serialVersionUID = 15242343256L;

  public ForbiddenException(String message) {
    super(message);
  }
}
