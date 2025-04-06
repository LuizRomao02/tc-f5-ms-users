package com.java.fiap.users.application.service;

import com.java.fiap.users.application.dto.DoctorDTO;
import com.java.fiap.users.application.dto.filter.DoctorFilter;
import com.java.fiap.users.application.dto.form.DoctorForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {

  DoctorDTO addDoctor(DoctorForm doctorForm);

  DoctorDTO getDoctor(String doctorId);

  Page<DoctorDTO> getAllDoctors(Pageable pageable, DoctorFilter doctorFilter);

  DoctorDTO updateDoctor(String doctorId, DoctorForm doctorForm);

  void deleteDoctor(String doctorId);
}
