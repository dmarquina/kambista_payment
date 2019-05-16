package com.dmarquina.kambistapayment.dto.response;

import com.dmarquina.kambistapayment.model.PaymentMethod;

import lombok.Data;

import org.springframework.beans.BeanUtils;

@Data
public class PaymentMethodResponse {
  private Long id;
  private String country;
  private String currency;

  public PaymentMethodResponse() {

  }

  public PaymentMethodResponse(PaymentMethod paymentMethod) {
    BeanUtils.copyProperties(paymentMethod, this);
  }
}
