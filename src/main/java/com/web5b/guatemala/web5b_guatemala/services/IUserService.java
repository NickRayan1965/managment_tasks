package com.web5b.guatemala.web5b_guatemala.services;

import com.web5b.guatemala.web5b_guatemala.entities.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
  Mono<User> findOneById(Long id);
  Flux<User> findAll();
  Mono<User> update(Long id, User user);
}
