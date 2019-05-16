package com.dmarquina.kambistapayment.service;

import com.dmarquina.kambistapayment.model.Payment;

import java.util.List;

public interface PaymentService {
  List<Payment> findAll();

  Payment create(Payment payment);

}
