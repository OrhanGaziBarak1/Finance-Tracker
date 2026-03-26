package com.example.finance_tracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class IncomeDTO {

  private Long id;

  @NotNull(message = "{validation.field.notBlank}")
  @Min(value = 1, message = "{validation.field.positive}")
  private BigDecimal amount;

  @NotBlank(message = "{validation.field.notBlank}")
  private String name;

  @NotNull(message = "{validation.field.notBlank}")
  private Long incomeCategoryId;

  @NotNull(message = "{validation.field.notBlank}")
  private LocalDate incomeDate;

}
