package com.example.finance_tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

  @NotBlank(message = "{validation.field.notBlank}")
  private String fullName;

  @NotBlank(message = "{validation.field.notBlank}")
  @Email(message = "{validation.field.email}")
  private String email;

  @NotBlank(message = "{validation.field.notBlank}")
  @Size(min = 6, max = 16, message = "{validation.register.password.size}")
  private String password;
}
