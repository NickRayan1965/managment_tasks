package com.web5b.guatemala.web5b_guatemala.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.web5b.guatemala.web5b_guatemala.dtos.req.LoginDto;
import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateUserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.LoginResponseDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.UserDto;
import com.web5b.guatemala.web5b_guatemala.entities.Role;
import com.web5b.guatemala.web5b_guatemala.security.decorators.HasAuthority;
import com.web5b.guatemala.web5b_guatemala.services.IAuthService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final IAuthService authService;

  @PostMapping("/login")
  public Mono<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
      return authService.login(loginDto);
  }

  @HasAuthority(Role.ADMIN)
  @PostMapping("/register")
  public Mono<UserDto> register(@RequestBody CreateUserDto createUserDto, Authentication authentication) {
      System.out.println(authentication);
      return authService.register(createUserDto);
  }
  
}
