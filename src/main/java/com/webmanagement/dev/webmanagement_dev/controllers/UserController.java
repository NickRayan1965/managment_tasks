package com.webmanagement.dev.webmanagement_dev.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.UserDto;
import com.webmanagement.dev.webmanagement_dev.entities.Role;
import com.webmanagement.dev.webmanagement_dev.security.decorators.HasAuthority;
import com.webmanagement.dev.webmanagement_dev.services.IUserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Users")
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
  public Mono<UserDto> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UpdateUserDto user) {
    return userService.update(id, user);
  }

  @HasAuthority(Role.ADMIN)
  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable(name = "id") Long id) {
    return userService.delete(id);
  }
}
