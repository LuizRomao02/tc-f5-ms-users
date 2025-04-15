package com.java.fiap.users.service.usecase.create.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.dto.form.PatientForm;
import com.java.fiap.users.application.service.usecase.create.impl.CreatePatientUseCaseImpl;
import com.java.fiap.users.domain.model.Patient;
import com.java.fiap.users.domain.repository.PatientRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CreatePatientUseCaseImplTest {

  @Mock private PatientRepository patientRepository;

  @InjectMocks private CreatePatientUseCaseImpl createPatientUseCase;

  private PatientForm patientForm;
  private Patient patient;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);

    patientForm =
        new PatientForm(
            "Maria",
            "Silva",
            LocalDate.now(),
            "12345678909",
            "(99) 99999-9999",
            "maria@email.com",
            "password");

    patient =
        Patient.builder()
            .id("id-paciente")
            .firstName("Maria")
            .lastName("Silva")
            .password("senha123")
            .birthDate(LocalDate.of(1990, 1, 15))
            .cpf("12345678909")
            .phone("(99) 99999-9999")
            .email("maria@email.com")
            .build();
  }

  @Test
  void shouldCreatePatientWithSuccess() {
    when(patientRepository.save(any(Patient.class))).thenReturn(patient);

    Patient result = createPatientUseCase.execute(patientForm);

    assertNotNull(result);
    assertEquals("Maria", result.getFirstName());
    assertEquals("Silva", result.getLastName());
    assertEquals("12345678909", result.getCpf());
    assertEquals("maria@email.com", result.getEmail());
    assertEquals("(99) 99999-9999", result.getPhone());
    assertEquals(LocalDate.of(1990, 1, 15), result.getBirthDate());

    verify(patientRepository, times(1)).save(any(Patient.class));
  }
}
