package com.dmarquina.kambistapayment.controller;

import com.dmarquina.kambistapayment.dto.request.LoginRequest;
import com.dmarquina.kambistapayment.dto.response.LoginResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Login")
@RestController
@RequestMapping("/login")
public class LoginController {

  @ApiOperation(value = "Inicio de sesión", notes = "Servicio para iniciar sesión")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Inicio correcto"),
      @ApiResponse(code = 400, message = "Solicitud inválida"),
      @ApiResponse(code = 500, message = "Error en el servidor") })
  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(new LoginResponse(getJWTToken(loginRequest.getUsername())));

  }

  private String getJWTToken(String username) {
    String secretKey = "mySecretKey";
    List<GrantedAuthority> grantedAuthorities =
        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

    String token = Jwts.builder()
        .setId("kambistaJWT")
        .setSubject(username)
        .claim("authorities", grantedAuthorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 600000))
        .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
        .compact();

    return "Bearer " + token;
  }
}
