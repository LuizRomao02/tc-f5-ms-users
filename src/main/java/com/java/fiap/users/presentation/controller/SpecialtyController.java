package com.java.fiap.users.presentation.controller;

import com.java.fiap.users.application.dto.SpecialtyDTO;
import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.application.dto.form.SpecialtyForm;
import com.java.fiap.users.application.service.SpecialtyService;
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
@RequestMapping(ApiMapping.SpecialtyApi.MAPPING)
@RequiredArgsConstructor
public class SpecialtyController {

  private final SpecialtyService specialtyService;

  @PostMapping(
      value = ApiMapping.Actions.ADD,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SpecialtyDTO> addSpecialty(@RequestBody SpecialtyForm specialtyForm) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(specialtyService.addSpecialty(specialtyForm));
  }

  @GetMapping(ApiMapping.Actions.LIST)
  public ResponseEntity<Page<SpecialtyDTO>> listSpecialties(
      @PageableDefault Pageable pageable,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) List<String> specialtiesIds,
      @RequestParam(required = false) List<String> doctorsIds,
      @RequestParam(required = false) String doctorName,
      @RequestParam(required = false) LocalDateTime createdAtStart,
      @RequestParam(required = false) LocalDateTime createdAtEnd) {
    SpecialtyFilter filter =
        SpecialtyFilter.builder()
            .name(name)
            .ids(specialtiesIds)
            .doctorsIds(doctorsIds)
            .doctorName(doctorName)
            .createdAtStart(createdAtStart)
            .createdAtEnd(createdAtEnd)
            .build();

    return ResponseEntity.status(HttpStatus.OK)
        .body(specialtyService.getAllSpecialties(pageable, filter));
  }

  @GetMapping(ApiMapping.Actions.DETAIL)
  public ResponseEntity<SpecialtyDTO> getSpecialtyDetail(@PathVariable String id) {
    return ResponseEntity.status(HttpStatus.OK).body(specialtyService.getSpecialty(id));
  }

  @PutMapping(ApiMapping.Actions.EDIT)
  public ResponseEntity<SpecialtyDTO> editSpecialty(
      @PathVariable String id, @RequestBody SpecialtyForm specialtyForm) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(specialtyService.updateSpecialty(id, specialtyForm));
  }

  @DeleteMapping(ApiMapping.Actions.DELETE)
  public ResponseEntity<Void> deleteSpecialty(@PathVariable String id) {
    specialtyService.deleteSpecialty(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
