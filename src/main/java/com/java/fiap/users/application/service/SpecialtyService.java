package com.java.fiap.users.application.service;

import com.java.fiap.users.application.dto.SpecialtyDTO;
import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.application.dto.form.SpecialtyForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecialtyService {

  SpecialtyDTO addSpecialty(SpecialtyForm specialtyForm);

  SpecialtyDTO getSpecialty(String specialtyId);

  Page<SpecialtyDTO> getAllSpecialties(Pageable pageable, SpecialtyFilter specialtyFilter);

  SpecialtyDTO updateSpecialty(String specialtyId, SpecialtyForm specialtyForm);

  void deleteSpecialty(String specialtyId);
}
