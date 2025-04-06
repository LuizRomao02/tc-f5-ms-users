package com.java.fiap.users.application.service.impl;

import com.java.fiap.users.application.dto.DoctorDTO;
import com.java.fiap.users.application.dto.filter.DoctorFilter;
import com.java.fiap.users.application.dto.form.DoctorForm;
import com.java.fiap.users.application.service.DoctorService;
import com.java.fiap.users.application.service.usecase.create.CreateDoctorUseCase;
import com.java.fiap.users.application.service.usecase.delete.DeleteDoctorUseCase;
import com.java.fiap.users.application.service.usecase.get.GetDoctorUseCase;
import com.java.fiap.users.application.service.usecase.get.GetListDoctorUseCase;
import com.java.fiap.users.application.service.usecase.update.UpdateDoctorUseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdateDoctorInput;
import com.java.fiap.users.application.util.ConverterToDTO;
import com.java.fiap.users.domain.model.Doctor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

  private final GetDoctorUseCase getDoctorUseCase;
  private final CreateDoctorUseCase createDoctorUseCase;
  private final UpdateDoctorUseCase updateDoctorUseCase;
  private final DeleteDoctorUseCase deleteDoctorUseCase;
  private final GetListDoctorUseCase getListDoctorUseCase;

  private final ConverterToDTO converterToDTO;

  @Override
  public DoctorDTO addDoctor(DoctorForm doctorForm) {
    Doctor doctor = createDoctorUseCase.execute(doctorForm);
    return converterToDTO.toDTO(doctor);
  }

  @Override
  public DoctorDTO getDoctor(String doctorId) {
    Doctor doctor = getDoctorUseCase.execute(doctorId);
    return converterToDTO.toDTO(doctor);
  }

  @Override
  public Page<DoctorDTO> getAllDoctors(Pageable pageable, DoctorFilter doctorFilter) {
    List<DoctorDTO> doctorsDTO =
        getListDoctorUseCase.execute(doctorFilter).stream().map(converterToDTO::toDTO).toList();

    int start = (int) pageable.getOffset();
    int end = Math.min(start + pageable.getPageSize(), doctorsDTO.size());
    List<DoctorDTO> pageContent = doctorsDTO.subList(start, end);

    return new PageImpl<>(pageContent, pageable, doctorsDTO.size());
  }

  @Override
  public DoctorDTO updateDoctor(String doctorId, DoctorForm doctorForm) {
    Doctor doctor = getDoctorUseCase.execute(doctorId);
    Doctor doctorUpdated =
        updateDoctorUseCase.execute(
            UpdateDoctorInput.builder().doctorForm(doctorForm).doctor(doctor).build());

    return converterToDTO.toDTO(doctorUpdated);
  }

  @Override
  public void deleteDoctor(String doctorId) {
    Doctor doctor = getDoctorUseCase.execute(doctorId);
    deleteDoctorUseCase.execute(doctor);
  }
}
