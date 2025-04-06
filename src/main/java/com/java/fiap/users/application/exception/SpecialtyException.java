package com.java.fiap.users.application.exception;

import org.springframework.http.HttpStatus;

public class SpecialtyException extends ApiException {

  public SpecialtyException(HttpStatus status, String message) {
    super(status, message);
  }
}
