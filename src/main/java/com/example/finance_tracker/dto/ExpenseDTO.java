package com.example.finance_tracker.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ExpenseDTO {
  private Long id;

  @NotNull
  private BigDecimal amount;

  @NotNull
  private String good;
  private String description;

  @NotNull
  private Long categoryId;
  private String categoryName;

  @NotNull
  private Long sourceId;
  private String sourceName;

  @NotNull
  private LocalDate expenseDate;
}
