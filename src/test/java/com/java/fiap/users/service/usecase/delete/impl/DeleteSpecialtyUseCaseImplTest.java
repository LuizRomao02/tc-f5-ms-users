package com.java.fiap.users.service.usecase.delete.impl;

import static org.mockito.Mockito.*;

import com.java.fiap.users.application.service.usecase.delete.impl.DeleteSpecialtyUseCaseImpl;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteSpecialtyUseCaseImplTest {

  @Mock private SpecialtyRepository specialtyRepository;

  @InjectMocks private DeleteSpecialtyUseCaseImpl deleteSpecialtyUseCase;

  private Specialty specialty;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);

    specialty = Specialty.builder().id("specialty-id-001").name("Dermatologia").build();
  }

  @Test
  void shouldDeleteSpecialtyWithSuccess() {
    deleteSpecialtyUseCase.execute(specialty);

    verify(specialtyRepository, times(1)).delete(specialty);
  }
}
