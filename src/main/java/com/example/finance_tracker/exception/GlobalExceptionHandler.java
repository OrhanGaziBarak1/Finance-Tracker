package com.example.finance_tracker.exception;

import com.example.finance_tracker.dto.ErrorDTO;
import com.example.finance_tracker.dto.ValidationErrorDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler(BaseAppException.class)
  public ResponseEntity<ErrorDTO> handleAppException(BaseAppException ex, Locale locale) {
    String message = messageSource.getMessage(ex.getMessageKey(), ex.getArgs(), locale) ;
    return ResponseEntity
        .status(ex.getStatus())
        .body(ErrorDTO.of(message));
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
