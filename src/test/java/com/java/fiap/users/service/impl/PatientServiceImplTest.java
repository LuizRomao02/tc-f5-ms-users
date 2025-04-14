package com.java.fiap.users.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.java.fiap.users.application.dto.PatientDTO;
import com.java.fiap.users.application.dto.filter.PatientFilter;
import com.java.fiap.users.application.dto.form.PatientForm;
import com.java.fiap.users.application.service.impl.PatientServiceImpl;
import com.java.fiap.users.application.service.usecase.create.CreatePatientUseCase;
import com.java.fiap.users.application.service.usecase.delete.DeletePatientUseCase;
import com.java.fiap.users.application.service.usecase.get.GetListPatientUseCase;
import com.java.fiap.users.application.service.usecase.get.GetPatientUseCase;
import com.java.fiap.users.application.service.usecase.update.UpdatePatientUseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdatePatientInput;
import com.java.fiap.users.application.util.ConverterToDTO;
import com.java.fiap.users.domain.model.Patient;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class PatientServiceImplTest {

  @Mock private GetPatientUseCase getPatientUseCase;
  @Mock private CreatePatientUseCase createPatientUseCase;
  @Mock private DeletePatientUseCase deletePatientUseCase;
  @Mock private UpdatePatientUseCase updatePatientUseCase;
  @Mock private GetListPatientUseCase getListPatientUseCase;
  @Mock private ConverterToDTO converterToDTO;

  @InjectMocks private PatientServiceImpl patientServiceImpl;

  private PatientDTO patientDTO;
  private PatientForm patientForm;
  private Patient patient;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    LocalDateTime now = Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime();

    patientDTO =
        PatientDTO.builder()
            .id("id-teste")
            .firstName("Luiz")
            .lastName("Romao")
            .email("luiz@email.com")
            .birthDate(LocalDate.now())
            .cpf("12345678909")
            .phone("(99) 99999-9999")
            .createdAt(now)
            .updatedAt(now)
            .build();

    patientForm =
        new PatientForm(
            "Luiz",
            "Romao",
            LocalDate.now(),
            "12345678909",
            "(99) 99999-9999",
            "luiz@email.com",
            "password");

    patient =
        Patient.builder()
            .firstName("Luiz")
            .lastName("Romao")
            .birthDate(LocalDate.now())
            .cpf("12345678909")
            .phone("(99) 99999-9999")
            .email("luiz@email.com")
            .build();
  }

  @Test
  void testAddPatient() {
    when(createPatientUseCase.execute(patientForm)).thenReturn(patient);
    when(converterToDTO.toDTO(patient)).thenReturn(patientDTO);

    PatientDTO result = patientServiceImpl.addPatient(patientForm);

    assertNotNull(result);
    assertEquals(patientDTO.getId(), result.getId());
    assertEquals(patientDTO.getEmail(), result.getEmail());

    verify(createPatientUseCase).execute(patientForm);
    verify(converterToDTO).toDTO(patient);
  }

  @Test
  void testGetPatient() {
    String patientId = "id-teste";
    when(getPatientUseCase.execute(patientId)).thenReturn(patient);
    when(converterToDTO.toDTO(patient)).thenReturn(patientDTO);

    PatientDTO result = patientServiceImpl.getPatient(patientId);

    assertNotNull(result);
    assertEquals(patientDTO.getId(), result.getId());

    verify(getPatientUseCase).execute(patientId);
    verify(converterToDTO).toDTO(patient);
  }

  @Test
  void testGetAllPatients() {
    Pageable pageable = PageRequest.of(0, 10);
    PatientFilter patientFilter = new PatientFilter();

    when(getListPatientUseCase.execute(patientFilter)).thenReturn(List.of(patient));
    when(converterToDTO.toDTO(patient)).thenReturn(patientDTO);

    Page<PatientDTO> result = patientServiceImpl.getAllPatients(pageable, patientFilter);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());
    assertEquals(patientDTO.getEmail(), result.getContent().get(0).getEmail());

    verify(getListPatientUseCase).execute(patientFilter);
    verify(converterToDTO).toDTO(patient);
  }

  @Test
  void testUpdatePatient() {
    String patientId = "id-teste";
    Patient updatedPatient = Patient.builder().email("novo@email.com").build();

    when(getPatientUseCase.execute(patientId)).thenReturn(patient);
    when(updatePatientUseCase.execute(
            UpdatePatientInput.builder().patientForm(patientForm).patient(patient).build()))
        .thenReturn(updatedPatient);
    when(converterToDTO.toDTO(updatedPatient)).thenReturn(patientDTO);

    PatientDTO result = patientServiceImpl.updatePatient(patientId, patientForm);

    assertNotNull(result);
    assertEquals(patientDTO.getId(), result.getId());

    verify(getPatientUseCase).execute(patientId);
    verify(updatePatientUseCase)
        .execute(UpdatePatientInput.builder().patientForm(patientForm).patient(patient).build());
    verify(converterToDTO).toDTO(updatedPatient);
  }

  @Test
  void testDeletePatient() {
    String patientId = "id-teste";

    when(getPatientUseCase.execute(patientId)).thenReturn(patient);

    patientServiceImpl.deletePatient(patientId);

    verify(getPatientUseCase).execute(patientId);
    verify(deletePatientUseCase).execute(patient);
  }
}
