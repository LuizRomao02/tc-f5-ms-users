package com.java.fiap.users.application.service.usecase.delete.impl;

import com.java.fiap.users.application.service.usecase.delete.DeleteDoctorUseCase;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteDoctorUseCaseImpl implements DeleteDoctorUseCase {

  private final DoctorRepository doctorRepository;

  @Override
  public Void execute(Doctor doctor) {
    doctorRepository.delete(doctor);
    return null;
  }
}
