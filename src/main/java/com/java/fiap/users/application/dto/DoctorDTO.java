package com.java.fiap.users.application.dto;

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
public class DoctorDTO {

  private String id;
  private String name;
  private String crm;
  private List<SpecialtyBasicDTO> specialties;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
