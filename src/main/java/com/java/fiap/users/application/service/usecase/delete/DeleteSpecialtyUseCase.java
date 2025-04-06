package com.java.fiap.users.application.service.usecase.delete;

import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.domain.model.Specialty;

public interface DeleteSpecialtyUseCase extends UseCase<Specialty, Void> {

  @Override
  Void execute(Specialty specialty);
}
