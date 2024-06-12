package com.web5b.guatemala.web5b_guatemala.models;

public class ForbiddenException extends RuntimeException {
  private static final long serialVersionUID = 15242343256L;

  public ForbiddenException(String message) {
    super(message);
  }
}
