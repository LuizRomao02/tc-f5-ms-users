package com.java.fiap.users.application.service.usecase.create.impl;

import com.java.fiap.users.application.dto.form.PatientForm;
import com.java.fiap.users.common.NotifyUserRegister;
import com.java.fiap.users.application.service.usecase.create.CreatePatientUseCase;
import com.java.fiap.users.domain.model.Patient;
import com.java.fiap.users.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePatientUseCaseImpl implements CreatePatientUseCase {

  private final PatientRepository patientRepository;

  @Override
  @NotifyUserRegister
  public Patient execute(PatientForm form) {
    Patient patient =
        Patient.builder()
            .name(form.name())
            .birthDate(form.birthDate())
            .cpf(form.cpf())
            .phone(form.phone())
            .email(form.email())
            .build();

    return patientRepository.save(patient);
  }
}
