package com.example.finance_tracker.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseAppException {

  public NotFoundException(String messageKey, Object... args) {
    super(messageKey, HttpStatus.NOT_FOUND, args);
  }
}
