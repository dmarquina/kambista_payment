package com.dmarquina.kambistapayment.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginRequest {
  @NotEmpty(message = "Es encesario el username")
  private String username;
  @NotEmpty(message = "Es necesario la contraseña")
  private String password;
}
