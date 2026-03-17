package com.example.finance_tracker.controller;

import com.example.finance_tracker.dto.RegisterDTO;
import com.example.finance_tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @PostMapping("/register")
  public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO dto) {
    service.register(dto);
    return ResponseEntity.ok().build();
  }
}
