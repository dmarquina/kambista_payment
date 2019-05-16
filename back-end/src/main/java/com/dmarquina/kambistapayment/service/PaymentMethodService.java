package com.dmarquina.kambistapayment.service;

import com.dmarquina.kambistapayment.model.PaymentMethod;

import java.time.LocalDate;
import java.util.List;

public interface PaymentMethodService {
  List<PaymentMethod> findAll();

  List<PaymentMethod> findAllByIdUser(Long idUser);

  PaymentMethod create(PaymentMethod paymentMethod);

}
