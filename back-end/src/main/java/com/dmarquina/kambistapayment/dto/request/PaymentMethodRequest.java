package com.dmarquina.kambistapayment.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Valid
public class PaymentMethodRequest {
  private Long id;
  @NotEmpty(message = "Es necesario el tipo de moneda")
  private String currency;
  @NotEmpty(message = "Es necesario el país")
  private String country;
}
