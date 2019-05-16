package com.dmarquina.kambistapayment.dto.request;

import java.util.List;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserRequest {

  private long id;
  @NotEmpty(message = "Es encesario un nombre")
  private String name;
  @NotEmpty(message = "Es encesario un correo")
  private String email;
  @NotEmpty(message = "Es necesario el tipo de documento")
  private String docType;
  @NotEmpty(message = "Es necesario el numero de documento")
  private String docNumber;

  private List<PaymentMethodRequest> paymentMethods;

}
