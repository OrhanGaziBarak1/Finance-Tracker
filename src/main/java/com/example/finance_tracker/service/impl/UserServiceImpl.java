package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.config.JwtService;
import com.example.finance_tracker.config.SecurityUserDetailsService;
import com.example.finance_tracker.dto.LoginDTO;
import com.example.finance_tracker.dto.RegisterDTO;
import com.example.finance_tracker.dto.TokenDTO;
import com.example.finance_tracker.model.User;
import com.example.finance_tracker.repository.UserRepository;
import com.example.finance_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final SecurityUserDetailsService securityUserDetailsService;

  @Override
  public void register(RegisterDTO dto) {
    User user = new User();
    user.setEmail(dto.getEmail());
    user.setPassword(encoder.encode(dto.getPassword()));
    user.setFullName(dto.getFullName());
    repository.save(user);
  }

  @Override
  public TokenDTO login(LoginDTO dto) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
    );
    User user = repository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found!"));
    UserDetails userDetails = securityUserDetailsService.loadUserByUsername(user.getEmail());

    String token = jwtService.generateToken(userDetails);
    TokenDTO responseDto = new TokenDTO();
    responseDto.setToken(token);
    return responseDto;
  }

}
