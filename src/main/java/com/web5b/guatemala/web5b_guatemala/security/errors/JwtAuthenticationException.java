package com.web5b.guatemala.web5b_guatemala.security.errors;

import javax.naming.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
  public JwtAuthenticationException(String message) {
      super(message);
  }
}
