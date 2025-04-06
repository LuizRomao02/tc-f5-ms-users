package com.java.fiap.users.application.dto.filter;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorFilter {

  private List<String> ids;
  private String name;
  private String crm;
  private LocalDateTime createdAtStart;
  private LocalDateTime createdAtEnd;
}
