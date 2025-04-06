package com.java.fiap.users.application.service.usecase.update;

import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdateSpecialtyInput;
import com.java.fiap.users.domain.model.Specialty;

public interface UpdateSpecialtyUseCase extends UseCase<UpdateSpecialtyInput, Specialty> {

  @Override
  Specialty execute(UpdateSpecialtyInput updateSpecialtyInput);
}
