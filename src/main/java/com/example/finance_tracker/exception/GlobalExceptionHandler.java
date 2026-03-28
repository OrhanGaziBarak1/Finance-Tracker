package com.example.finance_tracker.exception;

import com.example.finance_tracker.dto.ErrorDTO;
import com.example.finance_tracker.dto.ValidationErrorDTO;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
        .map(FieldError::getDefaultMessage).toList();
    ValidationErrorDTO dto = new ValidationErrorDTO();
    dto.setMessages(errors);
    dto.setTimestamp(LocalDateTime.now());

    return ResponseEntity
        .status(ex.getStatusCode())
        .body(dto);
  }

}
