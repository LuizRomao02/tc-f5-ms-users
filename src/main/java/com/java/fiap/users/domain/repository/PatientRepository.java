package com.java.fiap.users.domain.repository;

import com.java.fiap.users.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository
    extends JpaRepository<Patient, String>, JpaSpecificationExecutor<Patient> {}
