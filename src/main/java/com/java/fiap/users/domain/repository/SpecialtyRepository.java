package com.java.fiap.users.domain.repository;

import com.java.fiap.users.domain.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository
    extends JpaRepository<Specialty, String>, JpaSpecificationExecutor<Specialty> {}
