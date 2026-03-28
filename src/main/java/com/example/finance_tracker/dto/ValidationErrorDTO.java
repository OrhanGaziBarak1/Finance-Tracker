package com.example.finance_tracker.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class ValidationErrorDTO {

  private List<String> messages;
  private LocalDateTime timestamp;

}
