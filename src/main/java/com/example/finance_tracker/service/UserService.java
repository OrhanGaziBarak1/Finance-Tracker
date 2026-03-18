package com.example.finance_tracker.service;

import com.example.finance_tracker.dto.LoginDTO;
import com.example.finance_tracker.dto.RegisterDTO;
import com.example.finance_tracker.dto.TokenDTO;

public interface UserService {
  void register(RegisterDTO dto);
  TokenDTO login(LoginDTO dto);
}
