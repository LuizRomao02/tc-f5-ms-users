package com.java.fiap.users.application.util;

import com.java.fiap.users.application.dto.DoctorDTO;
import com.java.fiap.users.application.dto.SpecialtyDTO;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.model.Specialty;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterToDTO {

  private final ModelMapper modelMapper;

  public DoctorDTO toDTO(Doctor doctor) {
    return modelMapper.map(doctor, DoctorDTO.class);
  }

  public SpecialtyDTO toDTO(Specialty specialty) {
    return modelMapper.map(specialty, SpecialtyDTO.class);
  }
}
