package com.example.finance_tracker.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseAppException extends RuntimeException {
  private final HttpStatus status;

  protected BaseAppException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
