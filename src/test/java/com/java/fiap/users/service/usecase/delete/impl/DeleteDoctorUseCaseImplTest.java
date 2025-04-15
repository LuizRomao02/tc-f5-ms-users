package com.java.fiap.users.service.usecase.delete.impl;

import static org.mockito.Mockito.*;

import com.java.fiap.users.application.service.usecase.delete.impl.DeleteDoctorUseCaseImpl;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteDoctorUseCaseImplTest {

  @Mock private DoctorRepository doctorRepository;

  @InjectMocks private DeleteDoctorUseCaseImpl deleteDoctorUseCase;

  private Doctor doctor;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);

    doctor =
        Doctor.builder()
            .id("doctor-id-123")
            .firstName("Carlos")
            .lastName("Souza")
            .crm("123456CRM")
            .email("carlos@email.com")
            .build();
  }

  @Test
  void shouldDeleteDoctorWithSuccess() {
    deleteDoctorUseCase.execute(doctor);

    verify(doctorRepository, times(1)).delete(doctor);
  }
}
