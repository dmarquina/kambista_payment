package com.dmarquina.kambistapayment.dto.response;

import com.dmarquina.kambistapayment.model.Payment;

import lombok.Data;

import org.springframework.beans.BeanUtils;

@Data
public class PaymentResponse {
  private Long id;
  private Long idUser;
  private Long idPaymentMethod;
  private double totalAmount;

  public PaymentResponse() {

  }

  public PaymentResponse(Payment payment) {
    BeanUtils.copyProperties(payment, this);
  }
}
