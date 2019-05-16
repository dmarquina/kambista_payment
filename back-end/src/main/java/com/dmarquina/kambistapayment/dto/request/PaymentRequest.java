package com.dmarquina.kambistapayment.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Valid
public class PaymentRequest {

  @NotNull(message = "Es necesario el usuario")
  private Long idUser;
  @NotNull(message = "Es necesario el metodo de pago")
  private Long idPaymentMethod;
  @NotNull(message = "Es necesario el monto total")
  private double totalAmount;
}
