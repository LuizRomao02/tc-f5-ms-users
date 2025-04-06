package com.java.fiap.users.application.service.usecase.get;

import com.java.fiap.users.application.dto.filter.DoctorFilter;
import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.domain.model.Doctor;
import java.util.List;

public interface GetListDoctorUseCase extends UseCase<DoctorFilter, List<Doctor>> {

  @Override
  List<Doctor> execute(DoctorFilter doctorFilter);
}
