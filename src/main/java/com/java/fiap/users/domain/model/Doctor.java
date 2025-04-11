package com.java.fiap.users.domain.model;

import jakarta.persistence.*;
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
public class Doctor extends BaseEntity {

  @Column(nullable = false)
  private String firstName;

  private String lastName;

  @Column(nullable = false)
  private String crm;

  @Column(nullable = false)
  private String email;

  @Transient private String password;

  @ManyToMany
  @JoinTable(
      name = "doctor_specialties",
      joinColumns = @JoinColumn(name = "doctor_id"),
      inverseJoinColumns = @JoinColumn(name = "specialty_id"))
  private Set<Specialty> specialties = new HashSet<>();
}
