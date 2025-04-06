package com.java.fiap.users.application.service.usecase.get;

import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.domain.model.Specialty;

public interface GetSpecialtyUseCase extends UseCase<String, Specialty> {

  @Override
  Specialty execute(String id);
}
