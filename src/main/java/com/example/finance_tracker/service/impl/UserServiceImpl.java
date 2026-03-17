package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.dto.RegisterDTO;
import com.example.finance_tracker.model.User;
import com.example.finance_tracker.repository.UserRepository;
import com.example.finance_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public void register(RegisterDTO dto) {
    User user = new User();
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());
    user.setFullName(dto.getFullName());
    repository.save(user);
  }
}
