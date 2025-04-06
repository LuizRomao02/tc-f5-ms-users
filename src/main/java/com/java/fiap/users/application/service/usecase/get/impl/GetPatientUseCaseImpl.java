package com.java.fiap.users.application.service.usecase.get.impl;

import com.java.fiap.users.application.exception.PatientException;
import com.java.fiap.users.application.service.usecase.get.GetPatientUseCase;
import com.java.fiap.users.domain.model.Patient;
import com.java.fiap.users.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPatientUseCaseImpl implements GetPatientUseCase {

  private final PatientRepository patientRepository;

  @Override
  public Patient execute(String id) {
    return patientRepository
        .findById(id)
        .orElseThrow(() -> new PatientException(HttpStatus.NOT_FOUND, "Patient not found"));
  }
}
