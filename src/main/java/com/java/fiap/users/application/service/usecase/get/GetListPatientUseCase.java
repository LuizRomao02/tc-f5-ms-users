package com.java.fiap.users.application.service.usecase.get;

import com.java.fiap.users.application.dto.filter.PatientFilter;
import com.java.fiap.users.application.service.usecase.UseCase;
import com.java.fiap.users.domain.model.Patient;
import java.util.List;

public interface GetListPatientUseCase extends UseCase<PatientFilter, List<Patient>> {

  @Override
  List<Patient> execute(PatientFilter patientFilter);
}
