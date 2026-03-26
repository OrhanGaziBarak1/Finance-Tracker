package com.example.finance_tracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class IncomeDTO {

  private Long id;

  @NotNull
  @Min(1)
  private BigDecimal amount;

  @NotNull
  private String name;

  @NotNull
  private Long incomeCategoryId;

  @NotNull
  private LocalDate incomeDate;

}
