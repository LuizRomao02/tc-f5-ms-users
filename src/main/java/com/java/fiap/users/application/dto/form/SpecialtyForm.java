package com.java.fiap.users.application.dto.form;

import jakarta.validation.constraints.NotBlank;

public record SpecialtyForm(@NotBlank String name) {}
