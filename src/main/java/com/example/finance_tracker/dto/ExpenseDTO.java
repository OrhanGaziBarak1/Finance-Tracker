package com.example.finance_tracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ExpenseDTO {
  private Long id;

  @NotNull(message = "{validation.field.notBlank}")
  @Min(value = 1, message = "{validation.field.positive}")
  private BigDecimal amount;

  @NotBlank(message = "{validation.field.notBlank}")
  private String good;
  private String description;

  @NotNull(message = "{validation.field.notBlank}")
  private Long categoryId;
  private String categoryName;

  @NotNull(message = "{validation.field.notBlank}")
  private Long sourceId;
  private String sourceName;

  @NotNull(message = "validation.fled.notBlank")
  private LocalDate expenseDate;
}
