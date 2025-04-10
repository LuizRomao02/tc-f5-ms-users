package com.java.fiap.users.application.dto.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record DoctorForm(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank String crm,
    @NotEmpty List<String> specialtyIds,
    @NotNull @Email String email,
    @NotBlank String password) {}
