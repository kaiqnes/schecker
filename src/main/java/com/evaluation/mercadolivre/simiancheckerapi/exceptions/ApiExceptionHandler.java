package com.evaluation.mercadolivre.simiancheckerapi.exceptions;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    ObjectError error = ex.getBindingResult().getAllErrors().get(0);

    ErrorMessage errorMessage = new ErrorMessage(error.getDefaultMessage(), "dna", status.value());

    errorMessage.setTimestamp(OffsetDateTime.now());

    return super.handleExceptionInternal(ex, errorMessage, headers, status, request);
  }
}
