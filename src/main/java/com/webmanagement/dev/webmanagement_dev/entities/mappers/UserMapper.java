package com.webmanagement.dev.webmanagement_dev.entities.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.UserDto;
import com.webmanagement.dev.webmanagement_dev.entities.User;

@Component
public class UserMapper implements IUserMapper {

  @Override
  public User dtoToEntity(CreateUserDto dto) {
    return User.builder()
        .username(dto.getUsername())
        .password(dto.getPassword())
        .role(dto.getRole())
        .enabled(true)
        .build();
  }

  @Override
  public User dtoToEntity(UpdateUserDto dto) {
    return User.builder()
        .username(dto.getUsername())
        .password(dto.getPassword())
        .role(dto.getRole())
        .enabled(dto.getEnabled())
        .build();
  }

  @Override
  public UserDto toDto(User entity) {
    return UserDto.builder()
        .id(entity.getId())
        .username(entity.getUsername())
        .role(entity.getRole())
        .enabled(entity.getEnabled())
        .build();
  }

  @Override
  public List<UserDto> toDto(List<User> entities) {
    return entities.stream().map(this::toDto).toList();
  }

  @Override
  public User dtoToEntity(UserDto dto) {
    return User.builder()
        .id(dto.getId())
        .username(dto.getUsername())
        .role(dto.getRole())
        .enabled(dto.getEnabled())
        .build();
  }

  @Override
  public User toEntity(CreateUserDto createUserDto) {
    return User.builder()
        .username(createUserDto.getUsername())
        .password(createUserDto.getPassword())
        .role(createUserDto.getRole())
        .enabled(true)
        .build();
  }

}
