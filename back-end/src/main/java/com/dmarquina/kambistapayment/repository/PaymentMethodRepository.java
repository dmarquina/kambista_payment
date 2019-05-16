package com.dmarquina.kambistapayment.repository;

import com.dmarquina.kambistapayment.model.PaymentMethod;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

  @Query("SELECT p FROM PaymentMethod p WHERE p.user.id = :idUser")
  List<PaymentMethod> getAllPaymentMethodsByIdUser(Long idUser);
}
