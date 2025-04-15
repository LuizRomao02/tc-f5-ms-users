package com.java.fiap.users.application.service.usecase.get.impl;

import com.java.fiap.users.application.dto.filter.DoctorFilter;
import com.java.fiap.users.application.service.specification.DoctorSpecification;
import com.java.fiap.users.application.service.usecase.get.GetListDoctorUseCase;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.repository.DoctorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetListDoctorUseCaseImpl implements GetListDoctorUseCase {

  private final DoctorRepository doctorRepository;
  private final DoctorSpecification doctorSpecification;

  @Override
  public List<Doctor> execute(DoctorFilter doctorFilter) {
    return doctorRepository.findAll(doctorSpecification.getFilter(doctorFilter));
  }
}
