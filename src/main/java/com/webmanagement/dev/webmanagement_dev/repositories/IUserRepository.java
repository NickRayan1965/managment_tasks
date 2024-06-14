package com.webmanagement.dev.webmanagement_dev.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.webmanagement.dev.webmanagement_dev.entities.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserRepository extends R2dbcRepository<User, Long> {

  Mono<UserDetails> findByUsername(String username);
  
  Mono<User> findOneByUsername(String username);

  @Query("select * from users where enabled = true")
  Flux<User> findAllEnabled();
}
