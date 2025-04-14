package com.java.fiap.users.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private String cpf;
  private String phone;
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
