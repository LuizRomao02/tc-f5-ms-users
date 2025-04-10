package com.java.fiap.users.application.service.usecase.update.impl;

import com.java.fiap.users.application.dto.form.PatientForm;
import com.java.fiap.users.application.service.usecase.update.UpdatePatientUseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdatePatientInput;
import com.java.fiap.users.domain.model.Patient;
import com.java.fiap.users.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePatientUseCaseImpl implements UpdatePatientUseCase {

  private final PatientRepository patientRepository;

  @Override
  public Patient execute(UpdatePatientInput updatePatientInput) {
    PatientForm form = updatePatientInput.getPatientForm();
    Patient patient = updatePatientInput.getPatient();

    patient.setFirstName(form.firstName());
    patient.setLastName(form.lastName());
    patient.setBirthDate(form.birthDate());
    patient.setCpf(form.cpf());
    patient.setPhone(form.phone());

    return patientRepository.save(patient);
  }
}
