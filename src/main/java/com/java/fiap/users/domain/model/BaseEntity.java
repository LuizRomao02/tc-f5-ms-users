package com.java.fiap.users.domain.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    createdAt = Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime();
    updatedAt = null;
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime();
  }
}
