package com.dmarquina.kambistapayment.repository;

import com.dmarquina.kambistapayment.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
