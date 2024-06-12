package com.web5b.guatemala.web5b_guatemala.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web5b.guatemala.web5b_guatemala.dtos.req.update.UpdateUserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.UserDto;
import com.web5b.guatemala.web5b_guatemala.entities.Role;
import com.web5b.guatemala.web5b_guatemala.security.decorators.HasAuthority;
import com.web5b.guatemala.web5b_guatemala.services.IUserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final IUserService userService;

  @HasAuthority(Role.ADMIN)
  @GetMapping("/{id}")
  public Mono<UserDto> findOneById(@PathVariable(name = "id") Long id) {
    return userService.findOneById(id);
  }

  @HasAuthority(Role.ADMIN)
  @GetMapping
  public Flux<UserDto> findAll() {
    return userService.findAll();
  }

  @HasAuthority(Role.ADMIN)
  @PutMapping("/{id}")
  public Mono<UserDto> update(@PathVariable(name = "id") Long id, UpdateUserDto user) {
    return userService.update(id, user);
  }

  @HasAuthority(Role.ADMIN)
  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable(name = "id") Long id) {
    return userService.delete(id);
  }
}
