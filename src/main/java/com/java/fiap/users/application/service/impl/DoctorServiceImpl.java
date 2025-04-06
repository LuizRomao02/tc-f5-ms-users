package com.java.fiap.users.application.service.impl;

import com.java.fiap.users.application.dto.DoctorDTO;
import com.java.fiap.users.application.dto.filter.DoctorFilter;
import com.java.fiap.users.application.dto.form.DoctorForm;
import com.java.fiap.users.application.service.DoctorService;
import com.java.fiap.users.application.service.usecase.CreateDoctorUseCase;
import com.java.fiap.users.application.util.ConverterToDTO;
import com.java.fiap.users.domain.model.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

  private final CreateDoctorUseCase createDoctorUseCase;
  private final ConverterToDTO converterToDTO;

  @Override
  public DoctorDTO addDoctor(DoctorForm doctorForm) {
    Doctor doctor = createDoctorUseCase.execute(doctorForm);
    return converterToDTO.toDTO(doctor);
  }

  @Override
  public DoctorDTO getDoctor(String doctorId) {
    return null;
  }

  @Override
  public Page<DoctorDTO> getAllDoctors(Pageable pageable, DoctorFilter doctorFilter) {
    return null;
  }

  @Override
  public DoctorDTO updateDoctor(String doctorId, DoctorForm doctorForm) {
    return null;
  }

  @Override
  public void deleteDoctor(String doctorId) {}
}
