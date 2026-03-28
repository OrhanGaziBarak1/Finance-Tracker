package com.example.finance_tracker.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends BaseAppException {

  public AuthorizationException(String message) {
    super(message, HttpStatus.UNAUTHORIZED);
  }
}
