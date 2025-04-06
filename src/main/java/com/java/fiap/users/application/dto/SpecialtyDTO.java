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
public class SpecialtyDTO {

  private String id;
  private String name;
  private List<DoctorDTO> doctors;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
