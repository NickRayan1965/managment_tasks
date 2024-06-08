package com.web5b.guatemala.web5b_guatemala.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.web5b.guatemala.web5b_guatemala.dtos.AuthResponseDto;
import com.web5b.guatemala.web5b_guatemala.dtos.LoginDto;
import com.web5b.guatemala.web5b_guatemala.entities.User;
import com.web5b.guatemala.web5b_guatemala.services.IAuthService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final IAuthService authService;

  @PostMapping("/login")
  public Mono<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
      return authService.login(loginDto);
  }

  @PostMapping("/register")
  public Mono<AuthResponseDto> register(@RequestBody User loginDto) {
      return authService.register(loginDto);
  }
  
}
