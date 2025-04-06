package com.java.fiap.users.application.service.usecase.impl;

import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.application.service.specification.SpecialtySpecification;
import com.java.fiap.users.application.service.usecase.GetListSpecialtyUseCase;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.SpecialtyRepository;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetListSpecialtyUseCaseImpl implements GetListSpecialtyUseCase {

  private final SpecialtyRepository specialtyRepository;
  private final SpecialtySpecification specialtySpecification;

  @Override
  public Set<Specialty> execute(SpecialtyFilter filter) {
    return new HashSet<>(specialtyRepository.findAll(specialtySpecification.getFilter(filter)));
  }
}
