package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.config.JwtService;
import com.example.finance_tracker.config.SecurityUserDetailsService;
import com.example.finance_tracker.dto.LoginDTO;
import com.example.finance_tracker.dto.RegisterDTO;
import com.example.finance_tracker.dto.TokenDTO;
import com.example.finance_tracker.entity.User;
import com.example.finance_tracker.exception.AuthorizationException;
import com.example.finance_tracker.repository.UserRepository;
import com.example.finance_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    Authentication auth;
    try {
      auth = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
      );
    } catch (AuthenticationException ex) {
      throw new AuthorizationException(ex.getMessage());
    }
    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    String token = jwtService.generateToken(userDetails);
    String refreshToken = jwtService.generateRefreshToken(userDetails);
    TokenDTO responseDto = new TokenDTO();
    responseDto.setToken(token);
    responseDto.setRefreshToken(refreshToken);
    return responseDto;
  }

  @Override
  public TokenDTO refresh(TokenDTO dto) {
    String userEmail = jwtService.extractUsername(dto.getRefreshToken());
    UserDetails userDetails = securityUserDetailsService.loadUserByUsername(userEmail);
    boolean isValid = jwtService.isTokenValid(dto.getRefreshToken(), userDetails);

    if(!isValid) {
      throw new AuthorizationException("Invalid token. Please re-login.");
    }

    String token = jwtService.generateToken(userDetails);
    String refreshToken = jwtService.generateRefreshToken(userDetails);
    TokenDTO responseDto = new TokenDTO();
    responseDto.setToken(token);
    responseDto.setRefreshToken(refreshToken);
    return responseDto;
  }

}
