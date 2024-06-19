package com.webmanagement.dev.webmanagement_dev.services;

import org.springframework.stereotype.Service;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.UserDto;
import com.webmanagement.dev.webmanagement_dev.entities.User;
import com.webmanagement.dev.webmanagement_dev.entities.mappers.IUserMapper;
import com.webmanagement.dev.webmanagement_dev.models.NotFoundException;
import com.webmanagement.dev.webmanagement_dev.repositories.IUserRepository;

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
        .map(userMapper::dtoToEntity)
        .flatMap(user -> {
          return getDtoVerified(userMapper.dtoToEntity(dto))
              .map(u -> user);
        })
        .doOnNext(user -> userMapper.mergeToEntity(dto, user))
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
