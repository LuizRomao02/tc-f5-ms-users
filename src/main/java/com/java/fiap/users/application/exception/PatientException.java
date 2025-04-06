package com.java.fiap.users.application.exception;

import org.springframework.http.HttpStatus;

public class PatientException extends ApiException {

  public PatientException(HttpStatus status, String message) {
    super(status, message);
  }
}
