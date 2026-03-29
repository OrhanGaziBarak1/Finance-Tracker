package com.example.finance_tracker.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends BaseAppException {

  public AuthorizationException(String messageKey, Object... args) {
    super(messageKey, HttpStatus.UNAUTHORIZED, args);
  }
}
