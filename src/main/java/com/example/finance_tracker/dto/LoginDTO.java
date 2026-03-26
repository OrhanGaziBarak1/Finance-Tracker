package com.example.finance_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
  @NotBlank(message = "{validation.field.notBlank}")
  private String email;

  @NotBlank(message = "{validation.field.notBlank}")
  private String password;
}
