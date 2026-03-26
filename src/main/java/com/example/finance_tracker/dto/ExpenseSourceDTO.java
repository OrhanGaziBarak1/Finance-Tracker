package com.example.finance_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExpenseSourceDTO {
  private Long id;

  @NotBlank(message = "{validation.field.notBlank}")
  private String name;
}
