package com.web5b.guatemala.web5b_guatemala.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web5b.guatemala.web5b_guatemala.entities.User;
import com.web5b.guatemala.web5b_guatemala.services.IUserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final IUserService userService;

  @GetMapping("/{id}")
  public Mono<User> findOneById(@PathVariable(name = "id") Long id) {
    return userService.findOneById(id);
  }

  @GetMapping
  public Flux<User> findAll() {
    return userService.findAll();
  }

  @PutMapping("/{id}")
  public Mono<User> update(@PathVariable(name = "id") Long id, User user) {
    return userService.update(id, user);
  }
}
