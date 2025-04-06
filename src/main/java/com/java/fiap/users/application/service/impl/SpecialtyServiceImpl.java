package com.java.fiap.users.application.service.impl;

import com.java.fiap.users.application.dto.SpecialtyDTO;
import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.application.dto.form.SpecialtyForm;
import com.java.fiap.users.application.service.SpecialtyService;
import com.java.fiap.users.application.service.usecase.create.CreateSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.delete.DeleteSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.get.GetListSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.get.GetSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.update.UpdateSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdateSpecialtyInput;
import com.java.fiap.users.application.util.ConverterToDTO;
import com.java.fiap.users.domain.model.Specialty;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SpecialtyServiceImpl implements SpecialtyService {

  private final GetSpecialtyUseCase getSpecialtyUseCase;
  private final CreateSpecialtyUseCase createSpecialtyUseCase;
  private final UpdateSpecialtyUseCase updateSpecialtyUseCase;
  private final DeleteSpecialtyUseCase deleteSpecialtyUseCase;
  private final GetListSpecialtyUseCase getListSpecialtyUseCase;

  private final ConverterToDTO converterToDTO;

  @Override
  public SpecialtyDTO addSpecialty(SpecialtyForm specialtyForm) {
    Specialty specialty = createSpecialtyUseCase.execute(specialtyForm);
    return converterToDTO.toDTO(specialty);
  }

  @Transactional(readOnly = true)
  @Override
  public SpecialtyDTO getSpecialty(String specialtyId) {
    Specialty specialty = getSpecialtyUseCase.execute(specialtyId);
    return converterToDTO.toDTO(specialty);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<SpecialtyDTO> getAllSpecialties(Pageable pageable, SpecialtyFilter specialtyFilter) {
    Set<Specialty> specialties = getListSpecialtyUseCase.execute(specialtyFilter);

    List<SpecialtyDTO> specialtiesDTO = specialties.stream().map(converterToDTO::toDTO).toList();

    int start = (int) pageable.getOffset();
    int end = Math.min(start + pageable.getPageSize(), specialties.size());
    List<SpecialtyDTO> pageContent = specialtiesDTO.subList(start, end);

    return new PageImpl<>(pageContent, pageable, specialties.size());
  }

  @Override
  public SpecialtyDTO updateSpecialty(String specialtyId, SpecialtyForm specialtyForm) {
    Specialty specialty = getSpecialtyUseCase.execute(specialtyId);
    Specialty specialtyUpdated =
        updateSpecialtyUseCase.execute(
            UpdateSpecialtyInput.builder().form(specialtyForm).specialty(specialty).build());

    return converterToDTO.toDTO(specialtyUpdated);
  }

  @Override
  public void deleteSpecialty(String specialtyId) {
    Specialty specialty = getSpecialtyUseCase.execute(specialtyId);
    deleteSpecialtyUseCase.execute(specialty);
  }
}
