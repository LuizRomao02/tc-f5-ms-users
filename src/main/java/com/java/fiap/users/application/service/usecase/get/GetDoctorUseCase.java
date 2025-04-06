package com.java.fiap.users.application.service.usecase.get;

import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.domain.model.Doctor;

public interface GetDoctorUseCase extends UseCase<String, Doctor> {

  @Override
  Doctor execute(String id);
}
