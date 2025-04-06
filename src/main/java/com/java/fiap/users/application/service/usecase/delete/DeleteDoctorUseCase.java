package com.java.fiap.users.application.service.usecase.delete;

import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.domain.model.Doctor;

public interface DeleteDoctorUseCase extends UseCase<Doctor, Void> {

  @Override
  Void execute(Doctor doctor);
}
