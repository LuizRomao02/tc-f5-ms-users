package com.java.fiap.users.application.service.usecase.create.impl;

import com.java.fiap.users.application.dto.form.SpecialtyForm;
import com.java.fiap.users.application.service.usecase.create.CreateSpecialtyUseCase;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSpecialtyUseCaseImpl implements CreateSpecialtyUseCase {

  private final SpecialtyRepository specialtyRepository;

  @Override
  public Specialty execute(SpecialtyForm specialtyForm) {
    Specialty specialty = Specialty.builder().name(specialtyForm.name()).build();
    return specialtyRepository.save(specialty);
  }
}
