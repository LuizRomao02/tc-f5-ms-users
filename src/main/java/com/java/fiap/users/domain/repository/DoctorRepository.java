package com.java.fiap.users.domain.repository;

import com.java.fiap.users.domain.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository
    extends JpaRepository<Doctor, String>, JpaSpecificationExecutor<Doctor> {}
