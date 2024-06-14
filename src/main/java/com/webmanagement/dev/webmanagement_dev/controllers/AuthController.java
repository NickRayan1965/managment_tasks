package com.webmanagement.dev.webmanagement_dev.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.webmanagement.dev.webmanagement_dev.dtos.req.LoginDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.LoginResponseDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.UserDto;
import com.webmanagement.dev.webmanagement_dev.entities.Role;
import com.webmanagement.dev.webmanagement_dev.security.decorators.HasAuthority;
import com.webmanagement.dev.webmanagement_dev.services.IAuthService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
  public Mono<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
      return authService.login(loginDto);
  }

  @SecurityRequirement(name = "BearerAuth")
  @HasAuthority(Role.ADMIN)
  @PostMapping("/register")
  public Mono<UserDto> register(@RequestBody CreateUserDto createUserDto) {
      return authService.register(createUserDto);
  }
  
}
