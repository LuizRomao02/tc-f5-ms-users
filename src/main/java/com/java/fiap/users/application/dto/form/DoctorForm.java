package com.java.fiap.users.application.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record DoctorForm(
    @NotBlank String name, @NotBlank String crm, @NotEmpty List<String> specialtyIds) {}
