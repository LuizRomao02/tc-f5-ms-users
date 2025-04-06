package com.java.fiap.users.application.service.usecase.update;

import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdateDoctorInput;
import com.java.fiap.users.domain.model.Doctor;

public interface UpdateDoctorUseCase extends UseCase<UpdateDoctorInput, Doctor> {

  @Override
  Doctor execute(UpdateDoctorInput updateDoctorInput);
}
