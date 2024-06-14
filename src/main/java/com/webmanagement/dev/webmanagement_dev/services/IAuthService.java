package com.webmanagement.dev.webmanagement_dev.services
;

import com.webmanagement.dev.webmanagement_dev.dtos.req.LoginDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.LoginResponseDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.UserDto;

import reactor.core.publisher.Mono;

//import org.springframework.security.core.userdetails.UserDetailsService;


public interface IAuthService {
  Mono<LoginResponseDto> login(LoginDto loginDto);
  Mono<UserDto> register(CreateUserDto createUserDto);
}
