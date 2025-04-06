package com.java.fiap.users.application.service.usecase;

public interface UseCase<I, O> {

  O execute(I input);
}
