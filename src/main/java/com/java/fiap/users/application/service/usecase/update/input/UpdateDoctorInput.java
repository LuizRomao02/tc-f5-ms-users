package com.java.fiap.users.application.service.usecase.update.input;

import com.java.fiap.users.application.dto.form.DoctorForm;
import com.java.fiap.users.domain.model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDoctorInput {

  private Doctor doctor;
  private DoctorForm doctorForm;
}
