package com.dmarquina.kambistapayment.controller;

import com.dmarquina.kambistapayment.dto.request.PaymentMethodRequest;
import com.dmarquina.kambistapayment.dto.response.PaymentMethodResponse;
import com.dmarquina.kambistapayment.model.PaymentMethod;
import com.dmarquina.kambistapayment.service.PaymentMethodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Metodos de pago")
@RestController
@RequestMapping("/paymentmethods")
public class PaymentMethodController {

  @Autowired
  PaymentMethodService paymentMethodService;

  @ApiOperation(value = "Listar metodo de pagos",
      notes = "Servicio para listar los metodo de pagos")
  @ApiResponses(
      value = { @ApiResponse(code = 201, message = "Metodos de pago listados correctamente"),
          @ApiResponse(code = 400, message = "Solicitud inválida"),
          @ApiResponse(code = 500, message = "Error en el servidor") })
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<PaymentMethodResponse>> findAllPaymentMethods() {
    return ResponseEntity.ok(paymentMethodService.findAll()
                                 .stream()
                                 .map(PaymentMethodResponse::new)
                                 .collect(Collectors.toList()));
  }

  @ApiOperation(value = "Listar metodo de pagos por usuario",
      notes = "Servicio para listar los metodos de pagos")
  @ApiResponses(
      value = { @ApiResponse(code = 201, message = "Metodos de pago listados correctamente"),
          @ApiResponse(code = 400, message = "Solicitud inválida"),
          @ApiResponse(code = 500, message = "Error en el servidor") })
  @GetMapping(value = "/{idUser}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<PaymentMethodResponse>> findAllPaymentMethodsByUser(
      @PathVariable Long idUser) {
    return ResponseEntity.ok(paymentMethodService.findAllByIdUser(idUser)
                                 .stream()
                                 .map(PaymentMethodResponse::new)
                                 .collect(Collectors.toList()));
  }

  @ApiOperation(value = "Crear metodo de pago",
      notes = "Servicio para crear un nuevo metodo de pago")
  @ApiResponses(
      value = { @ApiResponse(code = 201, message = "Metodo de pago  creado correctamente"),
          @ApiResponse(code = 400, message = "Solicitud inválida"),
          @ApiResponse(code = 500, message = "Error en el servidor") })
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<PaymentMethodResponse> createPaymentMethod(
      @RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
    PaymentMethod newPaymentMethod = new PaymentMethod();
    BeanUtils.copyProperties(paymentMethodRequest, newPaymentMethod);
    PaymentMethod paymentMethodCreated = paymentMethodService.create(newPaymentMethod);
    PaymentMethodResponse paymentMethodResponse = new PaymentMethodResponse(paymentMethodCreated);
    return ResponseEntity.ok(paymentMethodResponse);
  }

}
