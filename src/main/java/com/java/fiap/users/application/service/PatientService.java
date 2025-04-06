package com.java.fiap.users.application.service;

import com.java.fiap.users.application.dto.PatientDTO;
import com.java.fiap.users.application.dto.filter.PatientFilter;
import com.java.fiap.users.application.dto.form.PatientForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {

  PatientDTO addPatient(PatientForm patientForm);

  PatientDTO getPatient(String patientId);

  Page<PatientDTO> getAllPatients(Pageable pageable, PatientFilter patientFilter);

  PatientDTO updatePatient(String patientId, PatientForm patientForm);

  void deletePatient(String patientId);
}
