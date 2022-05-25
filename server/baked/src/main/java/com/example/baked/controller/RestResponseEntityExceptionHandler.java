package com.example.baked.controller;

import com.example.baked.controller.error.BadRequestException;
import com.example.baked.controller.error.UnauthorizedException;
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
  protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
    Map<String, String> responseBody = Map.of("error", "BAD REQUEST", "message", ex.getMessage());
    return handleExceptionInternal(
        ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = BadRequestException.class)
  protected ResponseEntity<Object> handleBadRequest(BadRequestException ex, WebRequest request) {
    Map<String, String> responseBody = Map.of("error", "BAD REQUEST", "message", ex.getMessage());
    return handleExceptionInternal(
        ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = UnauthorizedException.class)
  protected ResponseEntity<Object> handleUnauthorized(
      UnauthorizedException ex, WebRequest request) {
    Map<String, String> responseBody = Map.of("error", "UNAUTHORIZED", "message", ex.getMessage());
    return handleExceptionInternal(
        ex, responseBody, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
  }
}
