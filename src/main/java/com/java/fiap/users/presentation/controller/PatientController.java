package com.java.fiap.users.presentation.controller;

import com.java.fiap.users.application.dto.PatientDTO;
import com.java.fiap.users.application.dto.filter.PatientFilter;
import com.java.fiap.users.application.dto.form.PatientForm;
import com.java.fiap.users.application.service.PatientService;
import com.java.fiap.users.application.util.ApiMapping;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMapping.PatientApi.MAPPING)
@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;

  @PostMapping(
      value = ApiMapping.Actions.ADD,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientForm patientForm) {
    return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(patientForm));
  }

  @GetMapping(ApiMapping.Actions.LIST)
  public ResponseEntity<Page<PatientDTO>> listPatients(
      @PageableDefault Pageable pageable,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String cpf,
      @RequestParam(required = false) List<String> patientIds,
      @RequestParam(required = false) String phone,
      @RequestParam(required = false) LocalDateTime createdAtStart,
      @RequestParam(required = false) LocalDateTime createdAtEnd) {
    PatientFilter filter =
        PatientFilter.builder()
            .name(name)
            .ids(patientIds)
            .cpf(cpf)
            .phone(phone)
            .createdAtStart(createdAtStart)
            .createdAtEnd(createdAtEnd)
            .build();

    return ResponseEntity.status(HttpStatus.OK)
        .body(patientService.getAllPatients(pageable, filter));
  }

  @GetMapping(ApiMapping.Actions.DETAIL)
  public ResponseEntity<PatientDTO> getPatientDetail(@PathVariable String id) {
    return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatient(id));
  }

  @PutMapping(ApiMapping.Actions.EDIT)
  public ResponseEntity<PatientDTO> editPatient(
      @PathVariable String id, @RequestBody PatientForm patientForm) {
    return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(id, patientForm));
  }

  @DeleteMapping(ApiMapping.Actions.DELETE)
  public ResponseEntity<Void> deletePatient(@PathVariable String id) {
    patientService.deletePatient(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
