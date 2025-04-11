package com.java.fiap.users.presentation.controller;

import com.java.fiap.users.application.dto.SpecialtyDTO;
import com.java.fiap.users.application.dto.UserInfoDTO;
import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.application.dto.form.SpecialtyForm;
import com.java.fiap.users.application.service.SpecialtyService;
import com.java.fiap.users.application.util.ApiMapping;
import com.java.fiap.users.common.RoleName;
import io.swagger.v3.oas.annotations.Parameter;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMapping.SpecialtyApi.MAPPING)
@RequiredArgsConstructor
public class SpecialtyController {

  private final SpecialtyService specialtyService;

  @PreAuthorize("hasAnyRole('" + RoleName.DOCTOR + "')")
  @PostMapping(
      value = ApiMapping.Actions.ADD,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SpecialtyDTO> addSpecialty(
      @Parameter(hidden = true) @ModelAttribute UserInfoDTO userInfo,
      @RequestBody SpecialtyForm specialtyForm) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(specialtyService.addSpecialty(specialtyForm));
  }

  @PreAuthorize("hasAnyRole('" + RoleName.DOCTOR + "')")
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

  @PreAuthorize("hasAnyRole('" + RoleName.DOCTOR + "')")
  @GetMapping(ApiMapping.Actions.DETAIL)
  public ResponseEntity<SpecialtyDTO> getSpecialtyDetail(@PathVariable String id) {
    return ResponseEntity.status(HttpStatus.OK).body(specialtyService.getSpecialty(id));
  }

  @PreAuthorize("hasAnyRole('" + RoleName.DOCTOR + "')")
  @PutMapping(ApiMapping.Actions.EDIT)
  public ResponseEntity<SpecialtyDTO> editSpecialty(
      @PathVariable String id, @RequestBody SpecialtyForm specialtyForm) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(specialtyService.updateSpecialty(id, specialtyForm));
  }

  @PreAuthorize("hasAnyRole('" + RoleName.ADMIN + "')")
  @DeleteMapping(ApiMapping.Actions.DELETE)
  public ResponseEntity<Void> deleteSpecialty(@PathVariable String id) {
    specialtyService.deleteSpecialty(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
