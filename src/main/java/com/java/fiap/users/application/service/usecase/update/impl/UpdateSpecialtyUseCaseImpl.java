package com.java.fiap.users.application.service.usecase.update.impl;

import com.java.fiap.users.application.dto.form.SpecialtyForm;
import com.java.fiap.users.application.service.usecase.update.UpdateSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdateSpecialtyInput;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateSpecialtyUseCaseImpl implements UpdateSpecialtyUseCase {

  private final SpecialtyRepository specialtyRepository;

  @Override
  public Specialty execute(UpdateSpecialtyInput updateSpecialtyInput) {
    Specialty specialty = updateSpecialtyInput.getSpecialty();
    SpecialtyForm form = updateSpecialtyInput.getForm();

    specialty.setName(form.name());

    return specialtyRepository.save(specialty);
  }
}
