package com.java.fiap.users.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.java.fiap.users.application.dto.DoctorDTO;
import com.java.fiap.users.application.dto.SpecialtyBasicDTO;
import com.java.fiap.users.application.dto.filter.DoctorFilter;
import com.java.fiap.users.application.dto.form.DoctorForm;
import com.java.fiap.users.application.service.impl.DoctorServiceImpl;
import com.java.fiap.users.application.service.usecase.create.CreateDoctorUseCase;
import com.java.fiap.users.application.service.usecase.delete.DeleteDoctorUseCase;
import com.java.fiap.users.application.service.usecase.get.GetDoctorUseCase;
import com.java.fiap.users.application.service.usecase.get.GetListDoctorUseCase;
import com.java.fiap.users.application.service.usecase.update.UpdateDoctorUseCase;
import com.java.fiap.users.application.service.usecase.update.input.UpdateDoctorInput;
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
import org.springframework.data.domain.Pageable;

class DoctorServiceImplTest {

  @Mock private GetDoctorUseCase getDoctorUseCase;
  @Mock private CreateDoctorUseCase createDoctorUseCase;
  @Mock private UpdateDoctorUseCase updateDoctorUseCase;
  @Mock private DeleteDoctorUseCase deleteDoctorUseCase;
  @Mock private GetListDoctorUseCase getListDoctorUseCase;
  @Mock private ConverterToDTO converterToDTO;

  @InjectMocks private DoctorServiceImpl doctorServiceImpl;

  private DoctorDTO doctorDTO;
  private DoctorForm doctorForm;
  private Doctor doctor;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    SpecialtyBasicDTO specialtyBasicDTO =
        SpecialtyBasicDTO.builder().id("id-teste").name("Cardiologista").build();
    LocalDateTime now = Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime();

    doctorDTO =
        DoctorDTO.builder()
            .id("id-teste")
            .firstName("Luiz")
            .lastName("Romao")
            .email("luiz@email.com")
            .crm("123123123ET")
            .specialties(List.of(specialtyBasicDTO))
            .createdAt(now)
            .updatedAt(now)
            .build();

    doctorForm =
        new DoctorForm(
            "Luiz", "Romao", "123123123ET", List.of("id-teste"), "luiz@email.com", "password");

    Specialty specialty = Specialty.builder().name("Cardiologista").build();

    doctor =
        Doctor.builder()
            .firstName("Luiz")
            .lastName("Romao")
            .crm("123123123ET")
            .email("luiz@email.com")
            .specialties(Set.of(specialty))
            .build();
  }

  @Test
  void testAddDoctor() {
    when(createDoctorUseCase.execute(doctorForm)).thenReturn(doctor);
    when(converterToDTO.toDTO(doctor)).thenReturn(doctorDTO);

    DoctorDTO result = doctorServiceImpl.addDoctor(doctorForm);

    assertNotNull(result);
    assertEquals(doctorDTO.getId(), result.getId());
    assertEquals(doctorDTO.getFirstName(), result.getFirstName());
    assertEquals(doctorDTO.getLastName(), result.getLastName());
    assertEquals(doctorDTO.getCrm(), result.getCrm());
    assertEquals(doctorDTO.getEmail(), result.getEmail());

    verify(createDoctorUseCase).execute(doctorForm);
    verify(converterToDTO).toDTO(doctor);
  }

  @Test
  void testGetDoctor() {
    String doctorId = "id-teste";
    when(getDoctorUseCase.execute(doctorId)).thenReturn(doctor);
    when(converterToDTO.toDTO(doctor)).thenReturn(doctorDTO);

    DoctorDTO result = doctorServiceImpl.getDoctor(doctorId);

    assertNotNull(result);
    assertEquals(doctorDTO.getId(), result.getId());
    verify(getDoctorUseCase).execute(doctorId);
    verify(converterToDTO).toDTO(doctor);
  }

  @Test
  void testGetAllDoctors() {
    Pageable pageable = Pageable.ofSize(10).withPage(0);
    DoctorFilter doctorFilter = new DoctorFilter();

    when(getListDoctorUseCase.execute(doctorFilter)).thenReturn(List.of(doctor));
    when(converterToDTO.toDTO(doctor)).thenReturn(doctorDTO);

    Page<DoctorDTO> result = doctorServiceImpl.getAllDoctors(pageable, doctorFilter);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());
    assertEquals(doctorDTO.getId(), result.getContent().get(0).getId());

    verify(getListDoctorUseCase).execute(doctorFilter);
    verify(converterToDTO).toDTO(doctor);
  }

  @Test
  void testUpdateDoctor() {
    String doctorId = "id-teste";
    Doctor updatedDoctor = Doctor.builder().firstName("NovoNome").build();

    when(getDoctorUseCase.execute(doctorId)).thenReturn(doctor);
    when(updateDoctorUseCase.execute(
            UpdateDoctorInput.builder().doctorForm(doctorForm).doctor(doctor).build()))
        .thenReturn(updatedDoctor);
    when(converterToDTO.toDTO(updatedDoctor)).thenReturn(doctorDTO);

    DoctorDTO result = doctorServiceImpl.updateDoctor(doctorId, doctorForm);

    assertNotNull(result);
    assertEquals(doctorDTO.getId(), result.getId());
    verify(getDoctorUseCase).execute(doctorId);
    verify(updateDoctorUseCase)
        .execute(UpdateDoctorInput.builder().doctorForm(doctorForm).doctor(doctor).build());
    verify(converterToDTO).toDTO(updatedDoctor);
  }

  @Test
  void testDeleteDoctor() {
    String doctorId = "id-teste";

    when(getDoctorUseCase.execute(doctorId)).thenReturn(doctor);

    doctorServiceImpl.deleteDoctor(doctorId);

    verify(getDoctorUseCase).execute(doctorId);
    verify(deleteDoctorUseCase).execute(doctor);
  }
}
