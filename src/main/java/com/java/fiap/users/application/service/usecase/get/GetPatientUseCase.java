package com.java.fiap.users.application.service.usecase.get;

import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.domain.model.Patient;

public interface GetPatientUseCase extends UseCase<String, Patient> {

  @Override
  Patient execute(String id);
}
