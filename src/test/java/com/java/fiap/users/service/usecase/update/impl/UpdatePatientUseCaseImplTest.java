package com.java.fiap.users.service.usecase.update.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.dto.form.PatientForm;
import com.java.fiap.users.application.service.usecase.update.impl.UpdatePatientUseCaseImpl;
import com.java.fiap.users.application.service.usecase.update.input.UpdatePatientInput;
import com.java.fiap.users.domain.model.Patient;
import com.java.fiap.users.domain.repository.PatientRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UpdatePatientUseCaseImplTest {

  @Mock private PatientRepository patientRepository;

  @InjectMocks private UpdatePatientUseCaseImpl updatePatientUseCase;

  private Patient patient;
  private PatientForm patientForm;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    patient =
        Patient.builder()
            .id("patient-001")
            .firstName("Maria")
            .lastName("Silva")
            .cpf("12345678909")
            .birthDate(LocalDate.of(1985, 5, 15))
            .phone("(99) 99999-9999")
            .email("maria@email.com")
            .build();

    patientForm =
        new PatientForm(
            "Maria",
            "Silva Costa",
            LocalDate.of(1985, 5, 15),
            "12345678909",
            "(99) 99999-9999",
            "maria@email.com",
            "newpassword");
  }

  @Test
  void shouldUpdatePatientSuccessfully() {
    when(patientRepository.save(any(Patient.class))).thenReturn(patient);

    Patient updatedPatient =
        updatePatientUseCase.execute(new UpdatePatientInput(patient, patientForm));

    assertNotNull(updatedPatient);
    assertEquals("Maria", updatedPatient.getFirstName());
    assertEquals("Silva Costa", updatedPatient.getLastName());
    assertEquals("(99) 99999-9999", updatedPatient.getPhone());

    verify(patientRepository, times(1)).save(patient);
  }

  @Test
  void shouldNotUpdatePatientWhenRepositoryFails() {
    when(patientRepository.save(any(Patient.class)))
        .thenThrow(new RuntimeException("Database error"));

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () -> {
              updatePatientUseCase.execute(new UpdatePatientInput(patient, patientForm));
            });

    assertEquals("Database error", exception.getMessage());

    verify(patientRepository, times(1)).save(patient);
  }
}
