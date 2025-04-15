package com.java.fiap.users.service.usecase.update.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.dto.form.SpecialtyForm;
import com.java.fiap.users.application.service.usecase.update.impl.UpdateSpecialtyUseCaseImpl;
import com.java.fiap.users.application.service.usecase.update.input.UpdateSpecialtyInput;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UpdateSpecialtyUseCaseImplTest {

  @Mock private SpecialtyRepository specialtyRepository;

  @InjectMocks private UpdateSpecialtyUseCaseImpl updateSpecialtyUseCase;

  private Specialty specialty;
  private SpecialtyForm specialtyForm;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    specialty = Specialty.builder().id("specialty-001").name("Cardiologista").build();

    specialtyForm = new SpecialtyForm("Ortopedista");
  }

  @Test
  void shouldUpdateSpecialtySuccessfully() {
    when(specialtyRepository.save(any(Specialty.class))).thenReturn(specialty);

    Specialty updatedSpecialty =
        updateSpecialtyUseCase.execute(new UpdateSpecialtyInput(specialty, specialtyForm));

    assertNotNull(updatedSpecialty);
    assertEquals("Ortopedista", updatedSpecialty.getName());

    verify(specialtyRepository, times(1)).save(specialty);
  }

  @Test
  void shouldNotUpdateSpecialtyWhenRepositoryFails() {
    when(specialtyRepository.save(any(Specialty.class)))
        .thenThrow(new RuntimeException("Database error"));

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () -> {
              updateSpecialtyUseCase.execute(new UpdateSpecialtyInput(specialty, specialtyForm));
            });

    assertEquals("Database error", exception.getMessage());

    verify(specialtyRepository, times(1)).save(specialty);
  }
}
