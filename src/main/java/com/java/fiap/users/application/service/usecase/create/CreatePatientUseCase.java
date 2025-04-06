package com.java.fiap.users.application.service.usecase.create;

import com.java.fiap.users.application.dto.form.PatientForm;
import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.domain.model.Patient;

public interface CreatePatientUseCase extends UseCase<PatientForm, Patient> {

  @Override
  Patient execute(PatientForm form);
}
