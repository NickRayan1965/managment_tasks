package com.web5b.guatemala.web5b_guatemala.services
;

import com.web5b.guatemala.web5b_guatemala.dtos.req.LoginDto;
import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateUserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.LoginResponseDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.UserDto;

import reactor.core.publisher.Mono;

//import org.springframework.security.core.userdetails.UserDetailsService;


public interface IAuthService {
  Mono<LoginResponseDto> login(LoginDto loginDto);
  Mono<UserDto> register(CreateUserDto createUserDto);
}
