package com.web5b.guatemala.web5b_guatemala.services
;
import com.web5b.guatemala.web5b_guatemala.dtos.AuthResponseDto;
import com.web5b.guatemala.web5b_guatemala.dtos.LoginDto;
import com.web5b.guatemala.web5b_guatemala.entities.User;

import reactor.core.publisher.Mono;

//import org.springframework.security.core.userdetails.UserDetailsService;


public interface IAuthService {
  Mono<AuthResponseDto> login(LoginDto loginDto);
  Mono<AuthResponseDto> register(User user);
}
