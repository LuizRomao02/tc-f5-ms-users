package com.java.fiap.users.application.service.usecase.update;

import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdatePatientInput;
import com.java.fiap.users.domain.model.Patient;

public interface UpdatePatientUseCase extends UseCase<UpdatePatientInput, Patient> {

  @Override
  Patient execute(UpdatePatientInput updatePatientInput);
}
