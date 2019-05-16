package com.dmarquina.kambistapayment.service.impl;

import com.dmarquina.kambistapayment.model.Payment;
import com.dmarquina.kambistapayment.repository.PaymentRepository;
import com.dmarquina.kambistapayment.service.PaymentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

  @Autowired
  private PaymentRepository paymentRepository;

  @Override
  public List<Payment> findAll() {
    return paymentRepository.findAll();
  }

  @Override
  @Transactional
  public Payment create(Payment payment) {
    return paymentRepository.save(payment);
  }

}
