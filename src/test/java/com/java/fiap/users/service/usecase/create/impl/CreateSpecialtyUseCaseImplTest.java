package com.java.fiap.users.service.usecase.create.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.dto.form.SpecialtyForm;
import com.java.fiap.users.application.service.usecase.create.impl.CreateSpecialtyUseCaseImpl;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CreateSpecialtyUseCaseImplTest {

  @Mock private SpecialtyRepository specialtyRepository;

  @InjectMocks private CreateSpecialtyUseCaseImpl createSpecialtyUseCase;

  private SpecialtyForm specialtyForm;
  private Specialty specialty;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);

    specialtyForm = new SpecialtyForm("Ortopedista");

    specialty = Specialty.builder().id("id-specialty").name("Ortopedista").build();
  }

  @Test
  void shouldCreateSpecialtyWithSuccess() {
    when(specialtyRepository.save(any(Specialty.class))).thenReturn(specialty);

    Specialty result = createSpecialtyUseCase.execute(specialtyForm);

    assertNotNull(result);
    assertEquals("Ortopedista", result.getName());
    assertEquals("id-specialty", result.getId());

    verify(specialtyRepository, times(1)).save(any(Specialty.class));
  }
}
