package com.java.fiap.users.application.service.impl;

import com.java.fiap.users.application.dto.PatientDTO;
import com.java.fiap.users.application.dto.filter.PatientFilter;
import com.java.fiap.users.application.dto.form.PatientForm;
import com.java.fiap.users.application.service.PatientService;
import com.java.fiap.users.application.service.usecase.create.CreatePatientUseCase;
import com.java.fiap.users.application.service.usecase.delete.DeletePatientUseCase;
import com.java.fiap.users.application.service.usecase.get.GetListPatientUseCase;
import com.java.fiap.users.application.service.usecase.get.GetPatientUseCase;
import com.java.fiap.users.application.service.usecase.update.UpdatePatientUseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdatePatientInput;
import com.java.fiap.users.application.util.ConverterToDTO;
import com.java.fiap.users.domain.model.Patient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

  private final GetPatientUseCase getPatientUseCase;
  private final CreatePatientUseCase createPatientUseCase;
  private final DeletePatientUseCase deletePatientUseCase;
  private final UpdatePatientUseCase updatePatientUseCase;
  private final GetListPatientUseCase getListPatientUseCase;

  private final ConverterToDTO converterToDTO;

  @Override
  public PatientDTO addPatient(PatientForm patientForm) {
    Patient patient = createPatientUseCase.execute(patientForm);
    return converterToDTO.toDTO(patient);
  }

  @Override
  public PatientDTO getPatient(String patientId) {
    Patient patient = getPatientUseCase.execute(patientId);
    return converterToDTO.toDTO(patient);
  }

  @Override
  public Page<PatientDTO> getAllPatients(Pageable pageable, PatientFilter patientFilter) {
    List<PatientDTO> patientsDTO =
        getListPatientUseCase.execute(patientFilter).stream().map(converterToDTO::toDTO).toList();

    int start = (int) pageable.getOffset();
    int end = Math.min(start + pageable.getPageSize(), patientsDTO.size());
    List<PatientDTO> pageContent = patientsDTO.subList(start, end);

    return new PageImpl<>(pageContent, pageable, patientsDTO.size());
  }

  @Override
  public PatientDTO updatePatient(String patientId, PatientForm patientForm) {
    Patient patient = getPatientUseCase.execute(patientId);
    Patient patientUpdated =
        updatePatientUseCase.execute(
            UpdatePatientInput.builder().patientForm(patientForm).patient(patient).build());

    return converterToDTO.toDTO(patientUpdated);
  }

  @Override
  public void deletePatient(String patientId) {
    Patient patient = getPatientUseCase.execute(patientId);
    deletePatientUseCase.execute(patient);
  }
}
