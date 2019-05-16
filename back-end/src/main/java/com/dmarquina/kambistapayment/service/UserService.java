package com.dmarquina.kambistapayment.service;

import com.dmarquina.kambistapayment.model.User;

import java.util.List;

public interface UserService {

  List<User> findAllUsers();

  User findById(Long id);

  User create(User user);
}
