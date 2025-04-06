package com.java.fiap.users.application.service.usecase.get.impl;

import com.java.fiap.users.application.dto.filter.PatientFilter;
import com.java.fiap.users.application.service.specification.PatientSpecification;
import com.java.fiap.users.application.service.usecase.get.GetListPatientUseCase;
import com.java.fiap.users.domain.model.Patient;
import com.java.fiap.users.domain.repository.PatientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetListPatientUseCaseImpl implements GetListPatientUseCase {

  private final PatientRepository patientRepository;
  private final PatientSpecification patientSpecification;

  @Override
  public List<Patient> execute(PatientFilter patientFilter) {
    return patientRepository.findAll(patientSpecification.getFilter(patientFilter));
  }
}
