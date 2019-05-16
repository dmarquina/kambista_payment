package com.dmarquina.kambistapayment.controller;

import com.dmarquina.kambistapayment.dto.request.PaymentRequest;
import com.dmarquina.kambistapayment.dto.response.PaymentResponse;
import com.dmarquina.kambistapayment.model.Payment;
import com.dmarquina.kambistapayment.service.PaymentService;

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

@Api(tags = "Pagos")
@RestController
@RequestMapping("/payments")
public class PaymentController {

  @Autowired
  PaymentService paymentService;

  @ApiOperation(value = "Listar pagos", notes = "Servicio para listar pagos")
  @ApiResponses(
      value = { @ApiResponse(code = 201, message = "Pagos listados correctamente"),
          @ApiResponse(code = 400, message = "Solicitud inválida"),
          @ApiResponse(code = 500, message = "Error en el servidor") })
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<PaymentResponse>> findAllPayments() {
    return ResponseEntity.ok(paymentService.findAll()
                                 .stream()
                                 .map(PaymentResponse::new)
                                 .collect(Collectors.toList()));
  }

  @ApiOperation(value = "Crear pago ", notes = "Servicio para crear un nuevo pago ")
  @ApiResponses(
      value = { @ApiResponse(code = 201, message = "Pago  creado correctamente"),
          @ApiResponse(code = 400, message = "Solicitud inválida"),
          @ApiResponse(code = 500, message = "Error en el servidor") })
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<PaymentResponse> createPayment(
      @RequestBody @Valid PaymentRequest paymentRequest) {
    Payment newPayment = new Payment();
    BeanUtils.copyProperties(paymentRequest, newPayment);
    Payment paymentCreated = paymentService.create(newPayment);
    PaymentResponse paymentResponse = new PaymentResponse(paymentCreated);
    return ResponseEntity.ok(paymentResponse);
  }

}
