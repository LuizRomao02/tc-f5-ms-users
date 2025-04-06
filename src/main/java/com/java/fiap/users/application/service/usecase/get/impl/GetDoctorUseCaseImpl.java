package com.java.fiap.users.application.service.usecase.get.impl;

import com.java.fiap.users.application.exception.DoctorException;
import com.java.fiap.users.application.service.usecase.get.GetDoctorUseCase;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDoctorUseCaseImpl implements GetDoctorUseCase {

  private final DoctorRepository doctorRepository;

  @Override
  public Doctor execute(String id) {
    return doctorRepository
        .findById(id)
        .orElseThrow(() -> new DoctorException(HttpStatus.NOT_FOUND, "Doctor not found"));
  }
}
