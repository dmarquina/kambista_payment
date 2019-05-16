package com.dmarquina.kambistapayment.service.impl;

import com.dmarquina.kambistapayment.model.User;
import com.dmarquina.kambistapayment.repository.UserRepository;
import com.dmarquina.kambistapayment.service.UserService;
import com.dmarquina.kambistapayment.util.exceptionhandler.UserNotFoundException;
import com.dmarquina.kambistapayment.util.exceptionhandler.UserServerErrorException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User findById(Long id) {
    Optional<User> opUser= userRepository.findById(id);
    if (opUser.isPresent()) {
      return userRepository.findById(id).get();
    } else {
      throw new UserNotFoundException("No se encontró el usuario.");

    }
  }

  @Override
  @Transactional
  public User create(User user) {
    try {
      return userRepository.save(user);
    } catch (Exception e) {
      throw new UserServerErrorException("Hubo un error interno al crear el usuario.");
    }
  }

}
