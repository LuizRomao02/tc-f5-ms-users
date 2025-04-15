package com.java.fiap.users.service.usecase.delete.impl;

import static org.mockito.Mockito.*;

import com.java.fiap.users.application.service.usecase.delete.impl.DeletePatientUseCaseImpl;
import com.java.fiap.users.domain.model.Patient;
import com.java.fiap.users.domain.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeletePatientUseCaseImplTest {

  @Mock private PatientRepository patientRepository;

  @InjectMocks private DeletePatientUseCaseImpl deletePatientUseCase;

  private Patient patient;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);

    patient =
        Patient.builder()
            .id("patient-id-001")
            .firstName("Ana")
            .lastName("Oliveira")
            .cpf("98765432100")
            .email("ana@email.com")
            .build();
  }

  @Test
  void shouldDeletePatientWithSuccess() {
    deletePatientUseCase.execute(patient);

    verify(patientRepository, times(1)).delete(patient);
  }
}
