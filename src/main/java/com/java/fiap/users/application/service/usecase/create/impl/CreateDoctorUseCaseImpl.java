package com.java.fiap.users.application.service.usecase.create.impl;

import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.application.dto.form.DoctorForm;
import com.java.fiap.users.application.exception.DoctorException;
import com.java.fiap.users.application.service.usecase.create.CreateDoctorUseCase;
import com.java.fiap.users.application.service.usecase.get.GetListSpecialtyUseCase;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.DoctorRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDoctorUseCaseImpl implements CreateDoctorUseCase {

  private final DoctorRepository doctorRepository;
  private final GetListSpecialtyUseCase getSpecialtyUseCase;

  @Override
  public Doctor execute(DoctorForm form) {
    SpecialtyFilter specialtyFilter = SpecialtyFilter.builder().ids(form.specialtyIds()).build();
    Set<Specialty> specialties = getSpecialtyUseCase.execute(specialtyFilter);

    if (specialties.isEmpty()) {
      throw new DoctorException(HttpStatus.NOT_FOUND, "No specialties found");
    }

    Doctor doctor =
        Doctor.builder().name(form.name()).crm(form.crm()).specialties(specialties).build();

    return doctorRepository.save(doctor);
  }
}
