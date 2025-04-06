package com.java.fiap.users.application.service.usecase.get.impl;

import com.java.fiap.users.application.exception.SpecialtyException;
import com.java.fiap.users.application.service.usecase.get.GetSpecialtyUseCase;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSpecialtyUseCaseImpl implements GetSpecialtyUseCase {

  private final SpecialtyRepository specialtyRepository;

  @Override
  public Specialty execute(String id) {
    return specialtyRepository
        .findById(id)
        .orElseThrow(() -> new SpecialtyException(HttpStatus.NOT_FOUND, "Specialty not found"));
  }
}
