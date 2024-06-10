package com.web5b.guatemala.web5b_guatemala.services;

import org.springframework.stereotype.Service;

import com.web5b.guatemala.web5b_guatemala.dtos.create.CreateUserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.UserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.update.UpdateUserDto;
import com.web5b.guatemala.web5b_guatemala.entities.User;
import com.web5b.guatemala.web5b_guatemala.entities.mappers.IUserMapper;
import com.web5b.guatemala.web5b_guatemala.models.NotFoundException;
import com.web5b.guatemala.web5b_guatemala.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
  private final IUserRepository userRepository;

  private final IUserMapper userMapper;

  @Override
  public Mono<UserDto> findOneById(Long id) {
    return userRepository.findById(id)
        .map(userMapper::toDto)
        .switchIfEmpty(Mono.error(new NotFoundException("User not found")));
  }

  @Override
  public Flux<UserDto> findAll() {
    return userRepository.findAllEnabled().map(userMapper::toDto);
  }

  @Override
  public Mono<UserDto> update(Long id, UpdateUserDto dto) {
    return findOneById(id)
        .flatMap(u -> getDtoVerified(userMapper.dtoToEntity(dto)))
        .flatMap(userRepository::save)
        .map(userMapper::toDto);
  }

  @Override
  public Mono<UserDto> create(CreateUserDto dto) {
    return getDtoVerified(userMapper.dtoToEntity(dto))
        .flatMap(userRepository::save)
        .map(userMapper::toDto);
  }

  @Override
  public Mono<Void> delete(Long id) {
    return findOneById(id)
        .map(userMapper::dtoToEntity)
        .flatMap(user -> {
          user.setEnabled(false);
          return userRepository.save(user);
        })
        .then();
  }

  @Override
  public Mono<User> getDtoVerified(User dto) {
    return Mono.just(dto);
  }

}
