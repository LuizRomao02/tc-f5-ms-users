package com.java.fiap.users.application.service.usecase.delete;

import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.domain.model.Patient;

public interface DeletePatientUseCase extends UseCase<Patient, Void> {

  @Override
  Void execute(Patient patient);
}
