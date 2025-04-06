package com.java.fiap.users.application.service.usecase.delete.impl;

import com.java.fiap.users.application.service.usecase.delete.DeleteSpecialtyUseCase;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSpecialtyUseCaseImpl implements DeleteSpecialtyUseCase {

  private final SpecialtyRepository specialtyRepository;

  @Override
  public Void execute(Specialty specialty) {
    specialtyRepository.delete(specialty);
    return null;
  }
}
