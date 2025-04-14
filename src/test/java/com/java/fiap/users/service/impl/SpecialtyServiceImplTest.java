package com.java.fiap.users.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.java.fiap.users.application.dto.DoctorBasicDTO;
import com.java.fiap.users.application.dto.SpecialtyDTO;
import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.application.dto.form.SpecialtyForm;
import com.java.fiap.users.application.exception.SpecialtyException;
import com.java.fiap.users.application.service.impl.SpecialtyServiceImpl;
import com.java.fiap.users.application.service.usecase.create.CreateSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.delete.DeleteSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.get.GetListSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.get.GetSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.update.UpdateSpecialtyUseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdateSpecialtyInput;
import com.java.fiap.users.application.util.ConverterToDTO;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.model.Specialty;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

class SpecialtyServiceImplTest {

  @Mock private GetSpecialtyUseCase getSpecialtyUseCase;
  @Mock private CreateSpecialtyUseCase createSpecialtyUseCase;
  @Mock private UpdateSpecialtyUseCase updateSpecialtyUseCase;
  @Mock private DeleteSpecialtyUseCase deleteSpecialtyUseCase;
  @Mock private GetListSpecialtyUseCase getListSpecialtyUseCase;
  @Mock private ConverterToDTO converterToDTO;

  @InjectMocks private SpecialtyServiceImpl specialtyService;

  private SpecialtyDTO specialtyDTO;
  private SpecialtyForm specialtyForm;
  private Specialty specialty;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    LocalDateTime now = Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime();

    DoctorBasicDTO doctorBasicDTO =
        DoctorBasicDTO.builder().id("id-teste").name("Luiz Romao").build();

    specialtyDTO =
        SpecialtyDTO.builder()
            .id("id-teste")
            .name("Cardiologista")
            .doctors(List.of(doctorBasicDTO))
            .createdAt(now)
            .updatedAt(now)
            .build();

    specialtyForm = new SpecialtyForm("Cardiologista");

    Doctor doctor =
        Doctor.builder()
            .firstName("Luiz")
            .lastName("Romao")
            .crm("123123123ET")
            .email("luiz@email.com")
            .build();

    specialty = Specialty.builder().name("Cardiologista").build();

    specialty.setDoctors(Set.of(doctor));
    doctor.setSpecialties(Set.of(specialty));
  }

  @Test
  void testAddSpecialty() {
    when(createSpecialtyUseCase.execute(specialtyForm)).thenReturn(specialty);
    when(converterToDTO.toDTO(specialty)).thenReturn(specialtyDTO);

    SpecialtyDTO result = specialtyService.addSpecialty(specialtyForm);

    assertNotNull(result);
    assertEquals(specialtyDTO.getName(), result.getName());

    verify(createSpecialtyUseCase).execute(specialtyForm);
    verify(converterToDTO).toDTO(specialty);
  }

  @Test
  void testGetSpecialty() {
    String specialtyId = "id-teste";

    when(getSpecialtyUseCase.execute(specialtyId)).thenReturn(specialty);
    when(converterToDTO.toDTO(specialty)).thenReturn(specialtyDTO);

    SpecialtyDTO result = specialtyService.getSpecialty(specialtyId);

    assertNotNull(result);
    assertEquals(specialtyDTO.getName(), result.getName());

    verify(getSpecialtyUseCase).execute(specialtyId);
    verify(converterToDTO).toDTO(specialty);
  }

  @Test
  void testGetAllSpecialties() {
    Pageable pageable = PageRequest.of(0, 10);
    SpecialtyFilter filter = new SpecialtyFilter();

    when(getListSpecialtyUseCase.execute(filter)).thenReturn(Set.of(specialty));
    when(converterToDTO.toDTO(specialty)).thenReturn(specialtyDTO);

    Page<SpecialtyDTO> result = specialtyService.getAllSpecialties(pageable, filter);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());
    assertEquals(specialtyDTO.getName(), result.getContent().get(0).getName());

    verify(getListSpecialtyUseCase).execute(filter);
    verify(converterToDTO).toDTO(specialty);
  }

  @Test
  void testUpdateSpecialty() {
    String specialtyId = "id-teste";
    Specialty updatedSpecialty = Specialty.builder().name("Atualizada").build();
    SpecialtyDTO updatedDTO = SpecialtyDTO.builder().id("id-teste").name("Atualizada").build();

    when(getSpecialtyUseCase.execute(specialtyId)).thenReturn(specialty);
    when(updateSpecialtyUseCase.execute(
            UpdateSpecialtyInput.builder().form(specialtyForm).specialty(specialty).build()))
        .thenReturn(updatedSpecialty);
    when(converterToDTO.toDTO(updatedSpecialty)).thenReturn(updatedDTO);

    SpecialtyDTO result = specialtyService.updateSpecialty(specialtyId, specialtyForm);

    assertNotNull(result);
    assertEquals("Atualizada", result.getName());

    verify(getSpecialtyUseCase).execute(specialtyId);
    verify(updateSpecialtyUseCase).execute(any(UpdateSpecialtyInput.class));
    verify(converterToDTO).toDTO(updatedSpecialty);
  }

  @Test
  void testDeleteSpecialtyWithoutDoctors() {
    String specialtyId = "id-teste";
    specialty.setDoctors(Set.of()); // sem médicos

    when(getSpecialtyUseCase.execute(specialtyId)).thenReturn(specialty);

    specialtyService.deleteSpecialty(specialtyId);

    verify(getSpecialtyUseCase).execute(specialtyId);
    verify(deleteSpecialtyUseCase).execute(specialty);
  }

  @Test
  void testDeleteSpecialtyWithDoctorsThrowsException() {
    String specialtyId = "id-teste";
    when(getSpecialtyUseCase.execute(specialtyId)).thenReturn(specialty);

    SpecialtyException exception =
        assertThrows(SpecialtyException.class, () -> specialtyService.deleteSpecialty(specialtyId));

    assertEquals(
        "It is not possible to delete a specialty that is still linked to doctors",
        exception.getMessage());
    assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());

    verify(getSpecialtyUseCase).execute(specialtyId);
    verify(deleteSpecialtyUseCase, never()).execute(any());
  }
}
