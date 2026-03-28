package com.example.finance_tracker.exception;

import com.example.finance_tracker.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BaseAppException.class)
  public ResponseEntity<ErrorDTO> handleAppException(BaseAppException ex) {
    return ResponseEntity
        .status(ex.getStatus())
        .body(ErrorDTO.of(ex.getMessage()));
  }

}
