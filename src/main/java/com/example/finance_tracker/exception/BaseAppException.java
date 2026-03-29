package com.example.finance_tracker.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseAppException extends RuntimeException {
  private final HttpStatus status;
  private final String messageKey;
  private final Object[] args;

  protected BaseAppException(String messageKey, HttpStatus status, Object... args) {
    super(messageKey);
    this.status = status;
    this.messageKey = messageKey;
    this.args = args;
  }
}
