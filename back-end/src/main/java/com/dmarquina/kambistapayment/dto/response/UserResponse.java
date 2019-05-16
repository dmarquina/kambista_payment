package com.dmarquina.kambistapayment.dto.response;

import com.dmarquina.kambistapayment.model.User;

import lombok.Data;

import org.springframework.beans.BeanUtils;

@Data
public class UserResponse {
  private Long id;
  private String name;
  private String email;
  private String docType;
  private String docNumber;

  public UserResponse(User user) {
    BeanUtils.copyProperties(user, this);
  }

}
