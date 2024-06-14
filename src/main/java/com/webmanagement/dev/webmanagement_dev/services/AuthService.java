package com.webmanagement.dev.webmanagement_dev.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.webmanagement.dev.webmanagement_dev.dtos.req.LoginDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.LoginResponseDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.UserDto;
import com.webmanagement.dev.webmanagement_dev.entities.Role;
import com.webmanagement.dev.webmanagement_dev.entities.mappers.IUserMapper;
import com.webmanagement.dev.webmanagement_dev.repositories.IUserRepository;
import com.webmanagement.dev.webmanagement_dev.security.jwt.JwtProvider;

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
