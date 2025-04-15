package com.java.fiap.users.service.usecase.update.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.application.dto.form.DoctorForm;
import com.java.fiap.users.application.exception.DoctorException;
import com.java.fiap.users.application.service.usecase.get.GetListSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.update.impl.UpdateDoctorUseCaseImpl;
import com.java.fiap.users.application.service.usecase.update.input.UpdateDoctorInput;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.DoctorRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class UpdateDoctorUseCaseImplTest {

  @Mock private DoctorRepository doctorRepository;
  @Mock private GetListSpecialtyUseCase getSpecialtyUseCase;

  @InjectMocks private UpdateDoctorUseCaseImpl updateDoctorUseCase;

  private Doctor doctor;
  private DoctorForm doctorForm;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    doctor =
        Doctor.builder()
            .id("doctor-001")
            .firstName("Luiz")
            .lastName("Romao")
            .crm("123123123ET")
            .email("luiz@email.com")
            .build();

    doctorForm =
        new DoctorForm(
            "Luiz", "Romao", "123123123ET", List.of("specialty-001"), "luiz@email.com", "password");
  }

  @Test
  void shouldUpdateDoctorWhenSpecialtiesAreFound() {
    Specialty specialty = Specialty.builder().id("specialty-001").name("Cardiology").build();
    SpecialtyFilter specialtyFilter =
        SpecialtyFilter.builder().ids(List.of("specialty-001")).build();

    when(getSpecialtyUseCase.execute(specialtyFilter)).thenReturn(Set.of(specialty));
    when(doctorRepository.save(doctor)).thenReturn(doctor);

    Doctor updatedDoctor = updateDoctorUseCase.execute(new UpdateDoctorInput(doctor, doctorForm));

    assertNotNull(updatedDoctor);
    assertEquals("Luiz", updatedDoctor.getFirstName());
    assertEquals("Romao", updatedDoctor.getLastName());
    assertEquals("123123123ET", updatedDoctor.getCrm());
    assertEquals(1, updatedDoctor.getSpecialties().size());
    assertTrue(updatedDoctor.getSpecialties().contains(specialty));

    verify(getSpecialtyUseCase, times(1)).execute(specialtyFilter);
    verify(doctorRepository, times(1)).save(doctor);
  }

  @Test
  void shouldThrowDoctorExceptionWhenNoSpecialtiesFound() {
    SpecialtyFilter specialtyFilter =
        SpecialtyFilter.builder().ids(List.of("specialty-001")).build();

    when(getSpecialtyUseCase.execute(specialtyFilter)).thenReturn(new HashSet<>());

    DoctorException exception =
        assertThrows(
            DoctorException.class,
            () -> updateDoctorUseCase.execute(new UpdateDoctorInput(doctor, doctorForm)));

    assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    assertEquals("No specialties found", exception.getMessage());

    verify(getSpecialtyUseCase, times(1)).execute(specialtyFilter);
    verify(doctorRepository, times(0)).save(doctor);
  }
}
