package com.web5b.guatemala.web5b_guatemala.services;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.web5b.guatemala.web5b_guatemala.dtos.AuthResponseDto;

import com.web5b.guatemala.web5b_guatemala.dtos.LoginDto;

import com.web5b.guatemala.web5b_guatemala.entities.User;

import com.web5b.guatemala.web5b_guatemala.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

  private final IUserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public Mono<AuthResponseDto> login(LoginDto loginDto) {
    return userRepository.findByUsername(loginDto.getUsername())
        .filter(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
        .map(user -> new AuthResponseDto("Bearer token"))
        .switchIfEmpty(Mono.error(new RuntimeException("Invalid credentials")));

  }

  @Override
  public Mono<AuthResponseDto> register(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEnabled(true);
    System.out.println(user);
    
    return userRepository.save(user)
      .map(u -> new AuthResponseDto("Bearer token"))
      //error pq ya existe
      ;
        
  }

}
