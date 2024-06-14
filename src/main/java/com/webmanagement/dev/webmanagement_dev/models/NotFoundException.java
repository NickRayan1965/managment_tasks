package com.webmanagement.dev.webmanagement_dev.models;

public class NotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1131231233143144252L;

  public NotFoundException(String message) {
    super(message);
  }
}
