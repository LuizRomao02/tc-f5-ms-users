package com.java.fiap.users.application.service.usecase;

import com.java.fiap.users.application.dto.form.DoctorForm;
import com.java.fiap.users.domain.model.Doctor;

public interface CreateDoctorUseCase extends UseCase<DoctorForm, Doctor> {

  @Override
  Doctor execute(DoctorForm form);
}
