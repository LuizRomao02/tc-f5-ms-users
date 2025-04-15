package com.java.fiap.users.service.usecase.get.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.exception.DoctorException;
import com.java.fiap.users.application.service.usecase.get.impl.GetDoctorUseCaseImpl;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.repository.DoctorRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class GetDoctorUseCaseImplTest {

  @Mock private DoctorRepository doctorRepository;

  @InjectMocks private GetDoctorUseCaseImpl getDoctorUseCase;

  private Doctor doctor;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    doctor =
        Doctor.builder()
            .id("doctor-id-123")
            .firstName("John")
            .lastName("Doe")
            .crm("123456CRM")
            .email("john@email.com")
            .build();
  }

  @Test
  void shouldReturnDoctorWhenIdExists() {
    when(doctorRepository.findById("doctor-id-123")).thenReturn(Optional.of(doctor));

    Doctor result = getDoctorUseCase.execute("doctor-id-123");

    assertNotNull(result);
    assertEquals("doctor-id-123", result.getId());
    assertEquals("John", result.getFirstName());
    verify(doctorRepository, times(1)).findById("doctor-id-123");
  }

  @Test
  void shouldThrowExceptionWhenDoctorNotFound() {
    when(doctorRepository.findById("not-found")).thenReturn(Optional.empty());

    DoctorException exception =
        assertThrows(DoctorException.class, () -> getDoctorUseCase.execute("not-found"));

    assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    assertEquals("Doctor not found", exception.getMessage());
    verify(doctorRepository, times(1)).findById("not-found");
  }
}
