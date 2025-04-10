package com.java.fiap.users.application.service.usecase.update.impl;

import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.application.dto.form.DoctorForm;
import com.java.fiap.users.application.exception.DoctorException;
import com.java.fiap.users.application.service.usecase.get.GetListSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.update.UpdateDoctorUseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdateDoctorInput;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.model.Specialty;
import com.java.fiap.users.domain.repository.DoctorRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateDoctorUseCaseImpl implements UpdateDoctorUseCase {

  private final DoctorRepository doctorRepository;
  private final GetListSpecialtyUseCase getSpecialtyUseCase;

  @Override
  public Doctor execute(UpdateDoctorInput updateDoctorInput) {
    DoctorForm form = updateDoctorInput.getDoctorForm();
    Doctor doctor = updateDoctorInput.getDoctor();

    SpecialtyFilter specialtyFilter = SpecialtyFilter.builder().ids(form.specialtyIds()).build();
    Set<Specialty> specialties = getSpecialtyUseCase.execute(specialtyFilter);

    if (specialties.isEmpty()) {
      throw new DoctorException(HttpStatus.NOT_FOUND, "No specialties found");
    }

    doctor.setSpecialties(specialties);
    doctor.setFirstName(form.firstName());
    doctor.setLastName(form.lastName());
    doctor.setCrm(form.crm());

    return doctorRepository.save(doctor);
  }
}
