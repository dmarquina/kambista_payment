package com.dmarquina.kambistapayment.controller;

import com.dmarquina.kambistapayment.dto.request.UserRequest;
import com.dmarquina.kambistapayment.dto.response.UserResponse;
import com.dmarquina.kambistapayment.model.User;
import com.dmarquina.kambistapayment.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Usuarios")
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @ApiOperation(value = "Listar usuarios", notes = "Servicio para listar las usuarios")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Usuarios listados correctamente"),
      @ApiResponse(code = 400, message = "Solicitud inválida"),
      @ApiResponse(code = 500, message = "Error en el servidor") })
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<UserResponse>> findAllUsers() {
    return ResponseEntity.ok(userService.findAllUsers()
                                 .stream()
                                 .map(UserResponse::new)
                                 .collect(Collectors.toList()));
  }

  @ApiOperation(value = "Crear usuario ", notes = "Servicio para crear un nuevo usuario ")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Usuario  creado correctamente"),
      @ApiResponse(code = 400, message = "Solicitud inválida"),
      @ApiResponse(code = 500, message = "Error en el servidor") })
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
    User newUser = new User();
    BeanUtils.copyProperties(userRequest, newUser);
    User userCreated = userService.create(newUser);
    UserResponse userResponse = new UserResponse(userCreated);
    return ResponseEntity.ok(userResponse);
  }

}
