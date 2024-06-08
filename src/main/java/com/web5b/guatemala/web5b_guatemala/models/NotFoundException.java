package com.web5b.guatemala.web5b_guatemala.models;

public class NotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public NotFoundException(String message) {
    super(message);
  }
}
