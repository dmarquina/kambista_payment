package com.dmarquina.kambistapayment.repository;

import com.dmarquina.kambistapayment.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
