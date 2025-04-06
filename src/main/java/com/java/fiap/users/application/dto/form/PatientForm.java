package com.java.fiap.users.application.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public record PatientForm(
    @NotBlank String name,
    @NotNull LocalDate birthDate,
    @NotBlank String cpf,
    @Pattern(
            regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
            message = "Phone number must be in the format (XX) XXXXX-XXXX")
        String phone) {}
