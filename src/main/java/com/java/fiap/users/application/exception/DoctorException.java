package com.java.fiap.users.application.exception;

import org.springframework.http.HttpStatus;

public class DoctorException extends ApiException {

  public DoctorException(HttpStatus status, String message) {
    super(status, message);
  }
}
