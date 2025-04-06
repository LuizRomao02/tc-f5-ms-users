package com.java.fiap.users.application.service.usecase.delete.impl;

import com.java.fiap.users.application.service.usecase.delete.DeletePatientUseCase;
import com.java.fiap.users.domain.model.Patient;
import com.java.fiap.users.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePatientUseCaseImpl implements DeletePatientUseCase {

  private final PatientRepository patientRepository;

  @Override
  public Void execute(Patient patient) {
    patientRepository.delete(patient);
    return null;
  }
}
