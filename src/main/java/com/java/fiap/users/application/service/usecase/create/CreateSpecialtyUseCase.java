package com.java.fiap.users.application.service.usecase.create;

import com.java.fiap.users.application.dto.form.SpecialtyForm;
import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.domain.model.Specialty;

public interface CreateSpecialtyUseCase extends UseCase<SpecialtyForm, Specialty> {

  @Override
  Specialty execute(SpecialtyForm specialtyForm);
}
