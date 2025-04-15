package com.java.fiap.users.service.usecase.create.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.dto.form.DoctorForm;
import com.java.fiap.users.application.exception.DoctorException;
import com.java.fiap.users.application.service.usecase.create.impl.CreateDoctorUseCaseImpl;
import com.java.fiap.users.application.service.usecase.get.GetListSpecialtyUseCase;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.DoctorRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class CreateDoctorUseCaseImplTest {

  @Mock private DoctorRepository doctorRepository;
  @Mock private GetListSpecialtyUseCase getSpecialtyUseCase;

  @InjectMocks private CreateDoctorUseCaseImpl createDoctorUseCase;

  private DoctorForm doctorForm;
  private Doctor doctor;
  private Specialty specialty;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    LocalDateTime now = Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime();

    doctorForm =
        new DoctorForm(
            "Luiz", "Romao", "123123123ET", List.of("id-teste"), "luiz@email.com", "password");

    specialty = Specialty.builder().name("Cardiologista").build();

    doctor =
        Doctor.builder()
            .id("id-teste")
            .firstName("Luiz")
            .lastName("Romao")
            .crm("123123123ET")
            .email("luiz@email.com")
            .createdAt(now)
            .updatedAt(null)
            .specialties(Set.of(specialty))
            .build();
  }

  @Test
  void shouldCreateDoctorWithSuccess() {
    when(getSpecialtyUseCase.execute(any())).thenReturn(Set.of(specialty));
    when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

    Doctor result = createDoctorUseCase.execute(doctorForm);

    assertNotNull(result);

    assertEquals("Luiz", result.getFirstName());
    assertEquals("Romao", result.getLastName());
    assertEquals("123123123ET", result.getCrm());
    assertEquals("luiz@email.com", result.getEmail());
    assertEquals(1, result.getSpecialties().size());

    verify(getSpecialtyUseCase, times(1)).execute(any());
    verify(doctorRepository, times(1)).save(any(Doctor.class));
  }

  @Test
  void shouldThrowExceptionWhenNoSpecialtiesFound() {
    when(getSpecialtyUseCase.execute(any())).thenReturn(Set.of());

    DoctorException exception =
        assertThrows(DoctorException.class, () -> createDoctorUseCase.execute(doctorForm));

    assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    assertEquals("No specialties found", exception.getMessage());

    verify(getSpecialtyUseCase, times(1)).execute(any());
    verify(doctorRepository, never()).save(any());
  }
}
