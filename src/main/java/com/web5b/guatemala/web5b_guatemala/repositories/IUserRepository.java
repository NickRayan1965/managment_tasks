package com.web5b.guatemala.web5b_guatemala.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.web5b.guatemala.web5b_guatemala.entities.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserRepository extends R2dbcRepository<User, Long> {

  Mono<UserDetails> findByUsername(String username);
  
  Mono<User> findOneByUsername(String username);

  @Query("select * from users where enabled = true")
  Flux<User> findAllEnabled();
}
