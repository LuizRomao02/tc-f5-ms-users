package com.java.fiap.users.service.usecase.get.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.dto.filter.PatientFilter;
import com.java.fiap.users.application.service.specification.PatientSpecification;
import com.java.fiap.users.application.service.usecase.get.impl.GetListPatientUseCaseImpl;
import com.java.fiap.users.domain.model.Patient;
import com.java.fiap.users.domain.repository.PatientRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

class GetListPatientUseCaseImplTest {

  @Mock private PatientRepository patientRepository;
  @Mock private PatientSpecification patientSpecification;

  @InjectMocks private GetListPatientUseCaseImpl getListPatientUseCase;

  private Patient patient;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    patient =
        Patient.builder()
            .id("patient-001")
            .firstName("John")
            .lastName("Doe")
            .cpf("12345678901")
            .email("john@email.com")
            .build();
  }

  @Test
  void shouldReturnListOfPatientsWhenFilterIsApplied() {
    PatientFilter patientFilter = new PatientFilter();

    Specification<Patient> specificationMock = mock(Specification.class);

    when(patientSpecification.getFilter(patientFilter)).thenReturn(specificationMock);
    when(patientRepository.findAll(specificationMock)).thenReturn(List.of(patient));

    List<Patient> result = getListPatientUseCase.execute(patientFilter);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("John", result.get(0).getFirstName());

    verify(patientSpecification, times(1)).getFilter(patientFilter);
    verify(patientRepository, times(1)).findAll(specificationMock);
  }
}
