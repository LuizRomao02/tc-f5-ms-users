package com.java.fiap.users.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Specialty extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToMany(mappedBy = "specialties")
  private Set<Doctor> doctors = new HashSet<>();
}
