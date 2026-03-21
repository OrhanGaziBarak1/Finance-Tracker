package com.example.finance_tracker.controller;

import com.example.finance_tracker.config.SecurityUserDetailsService;
import com.example.finance_tracker.dto.IncomeDTO;
import com.example.finance_tracker.service.IncomeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/income")
public class IncomeController {

  private final IncomeService service;

  @GetMapping
  public ResponseEntity<List<IncomeDTO>> getAll(
      @AuthenticationPrincipal SecurityUserDetailsService.UserPrincipal principal
  ) {
    return ResponseEntity.ok().body(service.getAll(principal.getUser()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<IncomeDTO> getOne(
      @PathVariable Long id,
      @AuthenticationPrincipal SecurityUserDetailsService.UserPrincipal principal
  ) {
    return ResponseEntity.ok().body(service.getOne(id, principal.getUser()));
  }

  @PostMapping
  public ResponseEntity<IncomeDTO> create(
      @RequestBody @Valid IncomeDTO dto,
      @AuthenticationPrincipal SecurityUserDetailsService.UserPrincipal principal
  ) {
    return ResponseEntity.ok().body(service.create(dto, principal.getUser()));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @PathVariable Long id,
      @AuthenticationPrincipal SecurityUserDetailsService.UserPrincipal principal
  ) {
    service.delete(id, principal.getUser());
    return ResponseEntity.noContent().build();
  }
}
