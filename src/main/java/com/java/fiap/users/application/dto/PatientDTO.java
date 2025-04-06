package com.java.fiap.users.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDTO {

  private String id;
  private String name;
  private LocalDate birthDate;
  private String cpf;
  private String phone;
  private List<SpecialtyBasicDTO> specialties;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
