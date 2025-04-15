package com.java.fiap.users.service.usecase.get.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.dto.filter.DoctorFilter;
import com.java.fiap.users.application.service.specification.DoctorSpecification;
import com.java.fiap.users.application.service.usecase.get.impl.GetListDoctorUseCaseImpl;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.repository.DoctorRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

class GetListDoctorUseCaseImplTest {

  @Mock private DoctorRepository doctorRepository;
  @Mock private DoctorSpecification doctorSpecification;

  @InjectMocks private GetListDoctorUseCaseImpl getListDoctorUseCase;

  private Doctor doctor;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    doctor =
        Doctor.builder()
            .id("doc-001")
            .firstName("Alice")
            .lastName("Martins")
            .crm("CRM-SP-987654")
            .email("alice@email.com")
            .build();
  }

  @Test
  void shouldReturnListOfDoctorsWhenFilterIsApplied() {
    DoctorFilter doctorFilter = new DoctorFilter();

    Specification<Doctor> specificationMock = mock(Specification.class);

    when(doctorSpecification.getFilter(doctorFilter)).thenReturn(specificationMock);
    when(doctorRepository.findAll(specificationMock)).thenReturn(List.of(doctor));

    List<Doctor> result = getListDoctorUseCase.execute(doctorFilter);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("Alice", result.get(0).getFirstName());

    verify(doctorSpecification, times(1)).getFilter(doctorFilter);
    verify(doctorRepository, times(1)).findAll(specificationMock);
  }
}
