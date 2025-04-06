package com.java.fiap.users.application.exception;

import java.io.Serial;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApiException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  private final HttpStatus status;

  protected ApiException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }
}
