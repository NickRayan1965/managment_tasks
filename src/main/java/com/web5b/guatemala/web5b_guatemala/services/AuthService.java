package com.web5b.guatemala.web5b_guatemala.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.web5b.guatemala.web5b_guatemala.dtos.req.LoginDto;
import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateUserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.LoginResponseDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.UserDto;
import com.web5b.guatemala.web5b_guatemala.entities.Role;
import com.web5b.guatemala.web5b_guatemala.entities.mappers.IUserMapper;
import com.web5b.guatemala.web5b_guatemala.repositories.IUserRepository;
import com.web5b.guatemala.web5b_guatemala.security.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

  private final IUserRepository userRepository;

  private final JwtProvider jwtProvider;

  private final IUserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  @Override
  public Mono<LoginResponseDto> login(LoginDto loginDto) {
    return userRepository.findOneByUsername(loginDto.getUsername())
        .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid username or password")))
        .filter(user -> user.isEnabled())
        .switchIfEmpty(Mono.error(new DisabledException("User is disabled talk to admin to enable your account")))
        .filter(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
        .map(user -> {
          return LoginResponseDto.builder()
              .user(userMapper.toDto(user))
              .jwt(jwtProvider.generateToken(user))
              .build();
        })
        .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid username or password")))
        ;
  }

  @Override
  public Mono<UserDto> register(CreateUserDto createUserDto) {
    createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
    createUserDto.setRole(createUserDto.getRole() == null ? Role.USER : createUserDto.getRole());
    return Mono.just(createUserDto)
        .map(userMapper::toEntity)
        .flatMap(userRepository::save)
        .map(userMapper::toDto);
  }

}
