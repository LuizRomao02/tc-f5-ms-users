package com.java.fiap.users.service.usecase.get.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.application.service.specification.SpecialtySpecification;
import com.java.fiap.users.application.service.usecase.get.impl.GetListSpecialtyUseCaseImpl;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.SpecialtyRepository;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

class GetListSpecialtyUseCaseImplTest {

  @Mock private SpecialtyRepository specialtyRepository;
  @Mock private SpecialtySpecification specialtySpecification;

  @InjectMocks private GetListSpecialtyUseCaseImpl getListSpecialtyUseCase;

  private Specialty specialty;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    specialty = Specialty.builder().id("specialty-001").name("Cardiology").build();
  }

  @Test
  void shouldReturnSetOfSpecialtiesWhenFilterIsApplied() {
    SpecialtyFilter specialtyFilter = new SpecialtyFilter();

    Specification<Specialty> specificationMock = mock(Specification.class);

    when(specialtySpecification.getFilter(specialtyFilter)).thenReturn(specificationMock);
    when(specialtyRepository.findAll(specificationMock)).thenReturn(List.of(specialty));

    Set<Specialty> result = getListSpecialtyUseCase.execute(specialtyFilter);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(specialty));

    verify(specialtySpecification, times(1)).getFilter(specialtyFilter);
    verify(specialtyRepository, times(1)).findAll(specificationMock);
  }
}
