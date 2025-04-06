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
public class SpecialtyFilter {

  private List<String> ids;
  private String name;
  private List<String> doctorsIds;
  private String doctorName;
  private LocalDateTime createdAtStart;
  private LocalDateTime createdAtEnd;
}
