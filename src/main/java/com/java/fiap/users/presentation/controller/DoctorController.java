package com.java.fiap.users.presentation.controller;

import com.java.fiap.users.application.dto.DoctorDTO;
import com.java.fiap.users.application.dto.filter.DoctorFilter;
import com.java.fiap.users.application.dto.form.DoctorForm;
import com.java.fiap.users.application.service.DoctorService;
import com.java.fiap.users.application.util.ApiMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMapping.DoctorApi.MAPPING)
@RequiredArgsConstructor
public class DoctorController {

  private final DoctorService doctorService;

  @PostMapping(
      value = ApiMapping.Actions.ADD,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DoctorDTO> addDoctor(@RequestBody DoctorForm doctorForm) {
    return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addDoctor(doctorForm));
  }

  @GetMapping(ApiMapping.Actions.LIST)
  public ResponseEntity<Page<DoctorDTO>> listDoctors(
      @PageableDefault Pageable pageable,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String crm) {
    DoctorFilter filter = DoctorFilter.builder().name(name).crm(crm).build();

    return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctors(pageable, filter));
  }

  @GetMapping(ApiMapping.Actions.DETAIL)
  public ResponseEntity<DoctorDTO> getDoctorDetail(@PathVariable String id) {
    return ResponseEntity.status(HttpStatus.OK).body(doctorService.getDoctor(id));
  }

  @PutMapping(ApiMapping.Actions.EDIT)
  public ResponseEntity<DoctorDTO> editDoctor(
      @PathVariable String id, @RequestBody DoctorForm doctorForm) {
    return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateDoctor(id, doctorForm));
  }

  @DeleteMapping(ApiMapping.Actions.DELETE)
  public ResponseEntity<Void> deleteDoctor(@PathVariable String id) {
    doctorService.deleteDoctor(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
