package com.web5b.guatemala.web5b_guatemala.services;

import org.springframework.stereotype.Service;

import com.web5b.guatemala.web5b_guatemala.entities.User;
import com.web5b.guatemala.web5b_guatemala.models.NotFoundException;
import com.web5b.guatemala.web5b_guatemala.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
  private final IUserRepository userRepository;

  @Override
  public Mono<User> findOneById(Long id) {
    return userRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("User not found")));
  }

  @Override
  public Flux<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Mono<User> update(Long id, User user) {
    return findOneById(id)
      .flatMap(e -> {
        user.setId(e.getId());
        return userRepository.save(user);
      });
  }

}
