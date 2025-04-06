package com.java.fiap.users.application.service.usecase.update.input;

import com.java.fiap.users.application.dto.form.PatientForm;
import com.java.fiap.users.domain.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePatientInput {

  private Patient patient;
  private PatientForm patientForm;
}
