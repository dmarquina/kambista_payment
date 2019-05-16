package com.dmarquina.kambistapayment.service.impl;

import com.dmarquina.kambistapayment.model.PaymentMethod;
import com.dmarquina.kambistapayment.repository.PaymentMethodRepository;
import com.dmarquina.kambistapayment.service.PaymentMethodService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("paymentMethodService")
public class PaymentMethodServiceImpl implements PaymentMethodService {

  @Autowired
  private PaymentMethodRepository paymentMethodRepository;

  @Override
  public List<PaymentMethod> findAll() {
    return paymentMethodRepository.findAll();
  }

  @Override
  public List<PaymentMethod> findAllByIdUser(Long idUser) {
    return paymentMethodRepository.getAllPaymentMethodsByIdUser(idUser);
  }

  @Override
  @Transactional
  public PaymentMethod create(PaymentMethod paymentMethod) {
    return paymentMethodRepository.save(paymentMethod);
  }

}
