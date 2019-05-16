package com.dmarquina.kambistapayment.service.impl;

import com.dmarquina.kambistapayment.model.User;
import com.dmarquina.kambistapayment.repository.UserRepository;
import com.dmarquina.kambistapayment.service.UserService;
import com.dmarquina.kambistapayment.util.exceptionhandler.UserNotFoundException;

import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
  @TestConfiguration
  static class EmployeeServiceImplTestContextConfiguration {

    @Bean
    public UserService userService() {
      return new UserServiceImpl();
    }
  }

  @Autowired
  UserService userService;

  @MockBean
  UserRepository userRepository;

  @Before
  public void setUp() {
    User user = new User();
    user.setId(new Long(1));
    user.setDocNumber("47485907");
    user.setDocType("DNI");
    user.setName("DIEGO MARQUINA");
    user.setEmail("mrdiego0892@gmail.com");

    Mockito.when(userRepository.findById(user.getId()))
        .thenReturn(Optional.of(user));
  }

  @Test
  public void findById_Successful() {
    User user = new User();
    user.setId(Long.parseLong("1"));
    user.setDocNumber("47485907");
    user.setDocType("DNI");
    user.setName("DIEGO MARQUINA");
    user.setEmail("mrdiego0892@gmail.com");
    Assert.assertThat(userService.findById(new Long(1)), CoreMatchers.is(user));
  }

  @Test(expected = UserNotFoundException.class)
  public void findById_Fail() {
    userService.findById(new Long(2));
  }

  @Test
  public void create() {
    //TODO:TODO
  }
}