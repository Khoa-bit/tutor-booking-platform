package com.example.baked.controller;

import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = RuntimeException.class)
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    Map<String, String> responseBody = Map.of("error", "Bad Request", "message", ex.getMessage());
    return handleExceptionInternal(
        ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
