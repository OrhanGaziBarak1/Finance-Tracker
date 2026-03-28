package com.example.finance_tracker.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ErrorDTO {

  private String message;
  private LocalDateTime timestamp;

  public static ErrorDTO of(String message) {
    ErrorDTO dto = new ErrorDTO();
    dto.setMessage(message);
    dto.setTimestamp(LocalDateTime.now());
    return dto;
  }

}
