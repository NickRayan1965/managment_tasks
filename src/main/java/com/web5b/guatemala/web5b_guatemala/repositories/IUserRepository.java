package com.web5b.guatemala.web5b_guatemala.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.web5b.guatemala.web5b_guatemala.entities.User;

import reactor.core.publisher.Mono;

public interface IUserRepository extends R2dbcRepository<User, Long> {
  Mono<User> findByUsername(String username);
}
