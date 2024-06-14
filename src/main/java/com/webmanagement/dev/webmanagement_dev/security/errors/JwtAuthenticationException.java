package com.webmanagement.dev.webmanagement_dev.security.errors;

import javax.naming.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
  public JwtAuthenticationException(String message) {
      super(message);
  }
}
