package com.java.fiap.users.application.service.usecase.update.input;

import com.java.fiap.users.application.dto.form.SpecialtyForm;
import com.java.fiap.users.domain.model.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSpecialtyInput {

  private Specialty specialty;
  private SpecialtyForm form;
}
