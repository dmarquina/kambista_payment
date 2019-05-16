package com.dmarquina.kambistapayment.util.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserServerErrorException extends RuntimeException {
  public UserServerErrorException(String message) {
    super(message);
  }
}