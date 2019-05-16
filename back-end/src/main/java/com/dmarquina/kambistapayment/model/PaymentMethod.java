package com.dmarquina.kambistapayment.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "payment_method")
public class PaymentMethod {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String currency;
  private String country;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idUser")
  private User user;

}
